package org.saozquick.net;

import androidx.lifecycle.MutableLiveData;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.saozquick.commom.BaseDto;
import org.saozquick.commom.Constants;

/**
 * @ClassName: BaseHttpSubscriber
 * @Description: Flowable 类型
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class BaseHttpSubscriber<T> implements Subscriber<BaseDto<T>> {

    /**
     * 自定义异常类
     */
    private ApiException exception;

    public BaseHttpSubscriber() {
        data = new MutableLiveData<>();
    }

    private MutableLiveData<BaseDto<T>> data;

    public MutableLiveData<BaseDto<T>> get() {
        return data;
    }

    public void set(BaseDto<T> t) {
        this.data.setValue(t);
    }

    public void onFinish(BaseDto<T> t) {
        set(t);
    }

    @Override
    public void onSubscribe(Subscription s) {
        // 观察者接收事件 = 1个 (使用 Flowable 解决背压 )
        s.request(1);
    }

    @Override
    public void onNext(BaseDto<T> t) {
        if (t.getErrorCode() == Constants.Server.SUCCESS_CODE) {
            onFinish(t);
        } else {
            exception = ExceptionEngine.handleException(new ServerException(t.getErrorCode(), t.getErrorMsg()));
            getErrorDto(exception);
        }
    }

    @Override
    public void onError(Throwable t) {
        exception =  ExceptionEngine.handleException(t);
        getErrorDto(exception);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 初始化错误的dto
     */
    private void getErrorDto(ApiException ex) {
        BaseDto<T> dto = new BaseDto<T>();
        dto.setErrorCode(ex.getCode());
        dto.setErrorMsg(ex.getMsg());
        onFinish(dto);
    }
}

