package org.saozquick.commom;

import androidx.lifecycle.MutableLiveData;

/**
 * @ClassName: IBaseViewModelEvent
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public interface IBaseViewModelEvent {

    void showLoading(String msg);

    void showLoading();

    void dismissLoading();

    MutableLiveData<BaseEvent.BaseActionEvent> getActionLiveData();
}
