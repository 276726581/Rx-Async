package com.timogroup.async.callback.map;

import com.timogroup.async.scheduler.ImmediateAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface ImmediateAsyncMap<T, R> extends RxAsyncMap<T, R>, ImmediateAsyncScheduler {

}
