package org.saozquick.commom;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseFragment
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;

    private BaseDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModelEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad || noLazy()) {
            // 将数据加载逻辑放到onResume()方法中
            initData();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dismissLoading();
        isFirstLoad = true;
    }

    /**
     * * 是否不使用懒加载
     * * 默认需要懒加载
     */
    protected boolean noLazy() {
        return false;
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化viewModel
     */
    protected abstract ViewModel initViewModel();

    /**
     * 初始化viewModel List
     */
    protected List<ViewModel> initViewModelList() {
        return null;
    }

    private void initViewModelEvent() {
        List<ViewModel> viewModelList = initViewModelList();
        if (viewModelList != null && viewModelList.size() > 0) {
            observeEvent(viewModelList);
        } else {
            ViewModel viewModel = initViewModel();
            if (viewModel != null) {
                List<ViewModel> modelList = new ArrayList<>();
                modelList.add(viewModel);
                observeEvent(modelList);
            }
        }
    }

    private void observeEvent(List<ViewModel> viewModelList) {
        for (ViewModel viewModel : viewModelList) {
            if (viewModel instanceof IBaseViewModelEvent) {
                IBaseViewModelEvent viewModelAction = (IBaseViewModelEvent) viewModel;
                viewModelAction.getActionLiveData().observe(this, new Observer<BaseEvent.BaseActionEvent>() {
                    @Override
                    public void onChanged(BaseEvent.BaseActionEvent baseActionEvent) {
                        if (baseActionEvent != null) {
                            switch (baseActionEvent.getAction()) {
                                case BaseEvent.BaseActionEvent.SHOW_LOADING_DIALOG: {
                                    startLoading(baseActionEvent.getMessage());
                                    break;
                                }
                                case BaseEvent.BaseActionEvent.DISMISS_LOADING_DIALOG: {
                                    dismissLoading();
                                    break;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    protected void startLoading() {
        startLoading(null);
    }

    protected void startLoading(String message) {
        loadingDialog = LoadingDialog.with()
                .setWidth(330)
                .setHeight(220)
                .setOutCancel(false);
        loadingDialog.show(getChildFragmentManager());
    }

    protected void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss(getChildFragmentManager());
        }
    }
}