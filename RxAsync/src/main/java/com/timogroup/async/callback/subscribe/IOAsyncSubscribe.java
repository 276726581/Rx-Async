package com.timogroup.async.callback.subscribe;

import com.timogroup.async.scheduler.IOAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface IOAsyncSubscribe<T> extends RxAsyncSubscribe<T>, IOAsyncScheduler {

}
