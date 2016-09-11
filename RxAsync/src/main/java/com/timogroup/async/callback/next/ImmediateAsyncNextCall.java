package com.timogroup.async.callback.next;

import com.timogroup.async.scheduler.ImmediateAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ImmediateAsyncNextCall<T, R> extends RxAsyncNextCall<T, R>, ImmediateAsyncScheduler {

}
