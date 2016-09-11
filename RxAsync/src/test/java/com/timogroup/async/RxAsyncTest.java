package com.timogroup.async;

import com.timogroup.async.callback.call.ComputationAsyncCall;
import com.timogroup.async.callback.next.IOAsyncNextCall;
import com.timogroup.async.callback.next.NewThreadAsyncNextCall;
import com.timogroup.async.callback.next.TrampolineAsyncNextCall;
import com.timogroup.async.callback.subscribe.NewThreadAsyncSubscribe;

import java.util.concurrent.CountDownLatch;

/**
 * Created by TimoRD on 2016/9/11.
 */
public class RxAsyncTest {

    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(1);
        RxAsync.call(new ComputationAsyncCall<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                System.out.println("1: " + name);
                return null;
            }
        }).next(new NewThreadAsyncNextCall<String, Short>() {
            @Override
            public Short call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("2: " + name);
                return null;
            }
        }).next(new IOAsyncNextCall<Short, Long>() {
            @Override
            public Long call(Short aShort) {
                String name = Thread.currentThread().getName();
                System.out.println("3: " + name);
                return null;
            }
        }).next(new TrampolineAsyncNextCall<Long, Object>() {
            @Override
            public Object call(Long aLong) {
                String name = Thread.currentThread().getName();
                System.out.println("4: " + name);
                return null;
            }
        }).subscribe(new NewThreadAsyncSubscribe<Object>() {
            @Override
            public void call(Object o) {
                String name = Thread.currentThread().getName();
                System.out.println("5: " + name);

                downLatch.countDown();
            }
        });

        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
