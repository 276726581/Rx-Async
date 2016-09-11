package com.timogroup.async.callback.call;

import com.timogroup.async.scheduler.ImmediateAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ImmediateAsyncCall<T> extends RxAsyncCall<T>, ImmediateAsyncScheduler {

}
