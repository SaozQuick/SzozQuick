package org.saozquick.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.saozquick.commom.BaseEvent;
import org.saozquick.commom.IBaseViewModelEvent;

import static org.saozquick.commom.BaseEvent.BaseActionEvent.SHOW_LOADING_DIALOG;

/**
 * @ClassName: BaseViewModel
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class BaseViewModel extends ViewModel implements IBaseViewModelEvent {

    private MutableLiveData<BaseEvent.BaseActionEvent> eventMutableLiveData = new MutableLiveData<>();

    @Override
    public void showLoading(String msg) {
        BaseEvent.BaseActionEvent baseActionEvent = new BaseEvent.BaseActionEvent(SHOW_LOADING_DIALOG);
        baseActionEvent.setMessage(msg);
        eventMutableLiveData.setValue(baseActionEvent);
    }

    @Override
    public void showLoading() {
        showLoading("");
    }

    @Override
    public void dismissLoading() {
        eventMutableLiveData.setValue(new BaseEvent.BaseActionEvent(BaseEvent.BaseActionEvent.DISMISS_LOADING_DIALOG));
    }

    @Override
    public MutableLiveData<BaseEvent.BaseActionEvent> getActionLiveData() {
        return eventMutableLiveData;
    }
}

