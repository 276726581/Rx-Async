package com.timogroup.async;

import com.timogroup.async.callback.call.RxAsyncCall;
import com.timogroup.async.callback.map.RxAsyncMap;
import com.timogroup.async.callback.next.RxAsyncNextCall;
import com.timogroup.async.callback.observer.RxAsyncObserver;
import com.timogroup.async.callback.subscribe.RxAsyncError;
import com.timogroup.async.callback.subscribe.RxAsyncSubscribe;
import com.timogroup.async.utl.SchedulerUtil;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.producers.QueuedProducer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by TimoRD on 2016/9/11.
 */
public final class RxAsync<T> {

    private Observable<T> observable;

    private RxAsync(Observable<T> observable) {
        this.observable = observable;
    }

    public static <T> RxAsync<T> call(RxAsyncCall<T> call) {
        Scheduler scheduler = SchedulerUtil.getScheduler(call);
        Observable<T> observable = Observable.fromCallable(call).subscribeOn(scheduler);

        return new RxAsync(observable);
    }

    public <R> RxAsync<R> next(RxAsyncNextCall<T, R> next) {
        Observable<R> nextObservable = observable.flatMap(new Func1<T, Observable<R>>() {
            @Override
            public Observable<R> call(T t) {
                Scheduler scheduler = SchedulerUtil.getScheduler(next);
                return Observable.fromCallable(new Callable<R>() {
                    @Override
                    public R call() throws Exception {
                        R result = next.call(t);
                        return result;
                    }
                }).subscribeOn(scheduler);
            }
        });

        return new RxAsync(nextObservable);
    }

    public <R> RxAsync<R> map(RxAsyncMap<T, R> map) {
        Observable<R> mapObservable = observable.flatMap(new Func1<T, Observable<R>>() {
            @Override
            public Observable<R> call(T t) {
                Scheduler scheduler = SchedulerUtil.getScheduler(map);
                return Observable.create(new Observable.OnSubscribe<R>() {
                    @Override
                    public void call(Subscriber<? super R> subscriber) {
                        try {
                            R[] result = map.call(t);
                            List<R> list = Arrays.asList(result);
                            if (list.isEmpty()) {
                                subscriber.onCompleted();
                            }

                            LinkedBlockingQueue<R> queue = new LinkedBlockingQueue<>();
                            queue.addAll(list);
                            subscriber.setProducer(new QueuedProducer(subscriber, queue));
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                }).subscribeOn(scheduler);
            }
        });

        return new RxAsync(mapObservable);
    }

    public RxAsync<T> filter(RxAsyncNextCall<T, Boolean> next) {
        Observable<T> nextObservable = observable.flatMap(new Func1<T, Observable<T>>() {
            @Override
            public Observable<T> call(T t) {
                Scheduler scheduler = SchedulerUtil.getScheduler(next);
                return Observable.just(t).filter(next).subscribeOn(scheduler);
            }
        });

        return new RxAsync(nextObservable);
    }

    public Observable<T> asObservable() {
        return observable;
    }

    public void subscribe(RxAsyncSubscribe<T> success) {
        Scheduler scheduler = SchedulerUtil.getScheduler(success);
        observable.observeOn(scheduler).subscribe(success);
    }

    public void subscribe(RxAsyncSubscribe<T> success, RxAsyncError error) {
        Scheduler scheduler = SchedulerUtil.getScheduler(success);
        observable.observeOn(scheduler).subscribe(success, error);
    }

    public void subscribe(RxAsyncObserver<T> observer) {
        Scheduler scheduler = SchedulerUtil.getScheduler(observer);
        observable.observeOn(scheduler).subscribe(observer);
    }
}
