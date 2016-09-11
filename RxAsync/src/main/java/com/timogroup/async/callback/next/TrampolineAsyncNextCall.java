package com.timogroup.async.callback.next;

import com.timogroup.async.scheduler.TrampolineAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface TrampolineAsyncNextCall<T, R> extends RxAsyncNextCall<T, R>, TrampolineAsyncScheduler {

}
