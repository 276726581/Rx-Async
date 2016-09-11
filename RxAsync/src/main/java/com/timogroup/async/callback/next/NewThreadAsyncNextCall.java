package com.timogroup.async.callback.next;

import com.timogroup.async.scheduler.NewThreadAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface NewThreadAsyncNextCall<T, R> extends RxAsyncNextCall<T, R>, NewThreadAsyncScheduler {

}
