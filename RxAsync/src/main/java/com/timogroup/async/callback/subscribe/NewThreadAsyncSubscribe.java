package com.timogroup.async.callback.subscribe;

import com.timogroup.async.scheduler.NewThreadAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface NewThreadAsyncSubscribe<T> extends RxAsyncSubscribe<T>, NewThreadAsyncScheduler {

}
