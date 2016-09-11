package com.timogroup.async.callback.call;

import com.timogroup.async.scheduler.NewThreadAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface NewThreadAsyncCall<T> extends RxAsyncCall<T>, NewThreadAsyncScheduler {

}
