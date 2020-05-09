package org.saozquick.repo;

/**
 * @ClassName: BaseRepository
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */

import org.saozquick.commom.BaseDto;
import org.saozquick.commom.IBaseViewModelEvent;
import org.saozquick.net.BaseHttpObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseRepository<T> {

    /**
     * RxJava Subscriber 回调
     */
    private BaseHttpObserver<T> baseHttpObserver;

    /**
     * 使用 Observable 解决背压(todo 是否无需解决背压，可直接使用 Observable,如需则使用 Flowable)
     */
    private Observable<BaseDto<T>> observable;

    /**
     * IBaseViewModelEvent 消息驱动UI对象
     */
    private IBaseViewModelEvent iBaseViewModelEvent;

    /**
     * 初始化 BaseRepository
     */
    public BaseRepository(IBaseViewModelEvent modelEvent) {
        baseHttpObserver = new BaseHttpObserver<>();
        this.iBaseViewModelEvent = modelEvent;
    }

    /**
     * 发送请求服务器事件
     */
    public BaseHttpObserver<T> send() {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iBaseViewModelEvent != null) {
                            iBaseViewModelEvent.showLoading("请求中");
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iBaseViewModelEvent != null) {
                            iBaseViewModelEvent.dismissLoading();
                        }
                    }
                })
                .subscribe(baseHttpObserver);
        return baseHttpObserver;
    }

    /**
     * 初始化 Observable
     */
    public BaseRepository<T> request(Observable<BaseDto<T>> observer) {
        this.observable = observer;
        return this;
    }
}
