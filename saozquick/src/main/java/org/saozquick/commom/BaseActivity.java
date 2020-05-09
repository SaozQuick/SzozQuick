package org.saozquick.commom;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    private BaseDialog loadingDialog;

    protected abstract ViewModel initViewModel();

    /**
     * init Views
     */
    protected abstract void initOnCreate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModelEvent();
        initOnCreate();
    }


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
    }

    protected void startLoading() {
        startLoading(null);
    }

    protected void startLoading(String message) {
        loadingDialog = LoadingDialog.with()
                .setWidth(330)
                .setHeight(220)
                .setOutCancel(false);
        loadingDialog.show(getSupportFragmentManager());
    }

    protected void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss(getSupportFragmentManager());
        }
    }
}