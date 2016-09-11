package com.timogroup.async.callback.next;

import com.timogroup.async.scheduler.IOAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface IOAsyncNextCall<T, R> extends RxAsyncNextCall<T, R>, IOAsyncScheduler {

}
