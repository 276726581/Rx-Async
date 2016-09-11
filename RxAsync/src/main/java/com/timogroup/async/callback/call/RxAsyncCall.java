package com.timogroup.async.callback.call;

import com.timogroup.async.scheduler.AsyncScheduler;

import java.util.concurrent.Callable;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface RxAsyncCall<T> extends Callable<T>, AsyncScheduler {

}
