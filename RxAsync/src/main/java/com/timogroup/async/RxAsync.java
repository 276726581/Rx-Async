package com.timogroup.async;

import rx.*;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.producers.QueuedProducer;
import rx.schedulers.Schedulers;

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

    public static <T> RxAsync<T> call(RxCall<T> call, Scheduler scheduler) {
        Observable<T> observable = Observable.fromCallable(new Callable<T>() {

            @Override
            public T call() throws Exception {
                T result = call.call();
                return result;
            }
        }).subscribeOn(scheduler);

        return new RxAsync(observable);
    }

    public <R> RxAsync<R> next(RxNext<T, R> next, Scheduler scheduler) {
        Observable<R> nextObservable = observable.observeOn(scheduler).lift(new Observable.Operator<R, T>() {
            @Override
            public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
                return new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                        try {
                            subscriber.onStart();
                            R result = next.call(t);
                            subscriber.onNext(result);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                };
            }
        });

        return new RxAsync(nextObservable);
    }

    public <R> RxAsync<R> flatMap(RxFlatMap<T, R> next, Scheduler scheduler) {
        Observable<R> nextObservable = observable.observeOn(scheduler).flatMap(new Func1<T, Observable<R>>() {
            @Override
            public Observable<R> call(T t) {
                return Observable.create(new Observable.OnSubscribe<R>() {
                    @Override
                    public void call(Subscriber<? super R> subscriber) {
                        try {
                            R[] result = next.call(t);
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
                });
            }
        });

        return new RxAsync(nextObservable);
    }

    public RxAsync<T> filter(RxFilter<T> next, Scheduler scheduler) {
        Observable<T> nextObservable = observable.observeOn(scheduler).lift(new Observable.Operator<T, T>() {
            @Override
            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                return new Subscriber<T>() {

                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                        try {
                            subscriber.onStart();
                            boolean accept = next.accept(t);
                            if (accept) {
                                subscriber.onNext(t);
                            }
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                };
            }
        });

        return new RxAsync(nextObservable);
    }

    public Observable<T> getObservable() {
        return observable;
    }

    public Subscription subscribe(RxSubscribe<T> success, Scheduler scheduler) {
        Subscription subscription = observable.observeOn(scheduler).subscribe(new Action1<T>() {
            @Override
            public void call(T t) {
                success.call(t);
            }
        });

        return subscription;
    }

    public Subscription subscribe(RxSubscribe<T> success, RxError error, Scheduler scheduler) {
        Subscription subscription = observable.observeOn(scheduler).subscribe(new Action1<T>() {
            @Override
            public void call(T t) {
                success.call(t);
            }
        }, new RxError() {
            @Override
            public void call(Throwable throwable) {
                error.call(throwable);
            }
        });

        return subscription;
    }

    public Subscription subscribe(RxObserver<T> observer, Scheduler scheduler) {
        Subscription subscription = observable.observeOn(scheduler).subscribe(new Observer<T>() {
            @Override
            public void onCompleted() {
                observer.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onNext(T t) {
                observer.onNext(t);
            }
        });

        return subscription;
    }

    public static <T> RxAsync<T> call(RxCall<T> call) {
        RxAsync<T> rxAsync = call(call, Schedulers.immediate());
        return rxAsync;
    }

    public <R> RxAsync<R> next(RxNext<T, R> next) {
        RxAsync<R> rxAsync = next(next, Schedulers.immediate());
        return rxAsync;
    }

    public <R> RxAsync<R> flatMap(RxFlatMap<T, R> flatMap) {
        RxAsync<R> rxAsync = flatMap(flatMap, Schedulers.immediate());
        return rxAsync;
    }

    public RxAsync<T> filter(RxFilter<T> next) {
        RxAsync<T> rxAsync = filter(next, Schedulers.immediate());
        return rxAsync;
    }
}
