package org.saozquick.net;

import androidx.lifecycle.MutableLiveData;

import org.saozquick.commom.Constants;
import org.saozquick.commom.BaseDto;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @ClassName: BaseHttpObserver
 * @Description: Observable 类型
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class BaseHttpObserver<T> implements Observer<BaseDto<T>> {

    /**
     * 自定义异常类
     */
    private ApiException exception;

    public BaseHttpObserver() {
        data = new MutableLiveData<>();
    }

    /**
     * 基于 BaseDto 的基本类型返回数据
     */
    private MutableLiveData<BaseDto<T>> data;

    /**
     * 获取调用返回的数据
     *
     * @return 返回数据，LiveData
     */
    public MutableLiveData<BaseDto<T>> get() {
        return data;
    }

    /**
     * 设置返回的数据
     *
     * @param t 需要设置的数据
     */
    public void set(BaseDto<T> t) {
        this.data.setValue(t);
    }

    @Override
    public void onSubscribe(Disposable d) {

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
    public void onError(Throwable e) {
        exception = ExceptionEngine.handleException(e);
        getErrorDto(exception);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 正常获取数据完成时
     *
     * @param t 获取到的数据
     */
    private void onFinish(BaseDto<T> t) {
        set(t);
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