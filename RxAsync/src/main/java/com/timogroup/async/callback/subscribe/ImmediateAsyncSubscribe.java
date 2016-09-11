package com.timogroup.async.callback.subscribe;

import com.timogroup.async.scheduler.ImmediateAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ImmediateAsyncSubscribe<T> extends RxAsyncSubscribe<T>, ImmediateAsyncScheduler {

}
