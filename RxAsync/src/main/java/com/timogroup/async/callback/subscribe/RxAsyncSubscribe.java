package com.timogroup.async.callback.subscribe;

import com.timogroup.async.scheduler.AsyncScheduler;
import rx.functions.Action1;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface RxAsyncSubscribe<T> extends Action1<T>, AsyncScheduler {

}
