package com.timogroup.async.callback.observer;

import com.timogroup.async.scheduler.AsyncScheduler;
import rx.Observer;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface RxAsyncObserver<T> extends Observer<T>, AsyncScheduler {

}
