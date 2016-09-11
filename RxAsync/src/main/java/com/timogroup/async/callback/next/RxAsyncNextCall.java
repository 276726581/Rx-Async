package com.timogroup.async.callback.next;

import com.timogroup.async.scheduler.AsyncScheduler;
import rx.functions.Func1;

/**
 * Created by TimoRD on 2016/9/11.
 */
public interface RxAsyncNextCall<T, R> extends Func1<T, R>, AsyncScheduler {

}
