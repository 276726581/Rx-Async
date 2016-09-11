package com.timogroup.async.utl;

import com.timogroup.async.scheduler.*;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by TimoRD on 2016/9/11.
 */
public final class SchedulerUtil {

    private SchedulerUtil() {

    }

    public static Scheduler getScheduler(AsyncScheduler asyncScheduler) {
        Scheduler scheduler;
        if (asyncScheduler instanceof ComputationAsyncScheduler) {
            scheduler = Schedulers.computation();
        } else if (asyncScheduler instanceof ImmediateAsyncScheduler) {
            scheduler = Schedulers.immediate();
        } else if (asyncScheduler instanceof IOAsyncScheduler) {
            scheduler = Schedulers.io();
        } else if (asyncScheduler instanceof NewThreadAsyncScheduler) {
            scheduler = Schedulers.newThread();
        } else if (asyncScheduler instanceof TrampolineAsyncScheduler) {
            scheduler = Schedulers.trampoline();
        } else {
            scheduler = Schedulers.immediate();
        }

        return scheduler;
    }
}
