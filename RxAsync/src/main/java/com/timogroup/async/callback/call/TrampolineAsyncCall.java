package com.timogroup.async.callback.call;

import com.timogroup.async.scheduler.TrampolineAsyncScheduler;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface TrampolineAsyncCall<T> extends RxAsyncCall<T>, TrampolineAsyncScheduler {

}
