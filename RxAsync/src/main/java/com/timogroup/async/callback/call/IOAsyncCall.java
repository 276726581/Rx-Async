package com.timogroup.async.callback.call;

import com.timogroup.async.scheduler.IOAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface IOAsyncCall<T> extends RxAsyncCall<T>, IOAsyncScheduler {

}
