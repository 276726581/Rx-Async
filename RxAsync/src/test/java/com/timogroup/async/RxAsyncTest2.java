package com.timogroup.async;

import com.timogroup.async.callback.call.NewThreadAsyncCall;
import com.timogroup.async.callback.map.ComputationAsyncMap;
import com.timogroup.async.callback.next.IOAsyncNextCall;
import com.timogroup.async.callback.subscribe.IOAsyncSubscribe;
import com.timogroup.async.callback.subscribe.RxAsyncError;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by TimoRD on 2016/9/11.
 */
public class RxAsyncTest2 {

    public static void main(String[] args) {
        int size = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        StringBuffer buffer = new StringBuffer();
        for (long i = 0; i < size; i++) {
            buffer.append(i).append(",");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        String s = buffer.toString();

        AtomicInteger count = new AtomicInteger();
        AtomicLong start = new AtomicLong();

        RxAsync.call(new NewThreadAsyncCall<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                System.out.println("1: " + name);
                return s;
            }
        }).map(new ComputationAsyncMap<String, String>() {
            @Override
            public String[] call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("2: " + name);

                String[] split = s.split(",");

                start.set(System.currentTimeMillis());

                return split;
            }
        }).next(new IOAsyncNextCall<String, String>() {
            @Override
            public String call(String s) {
                long end = System.currentTimeMillis();
                if ((end - start.get()) < 1000) {
                    String name = Thread.currentThread().getName();
                    System.out.println("4: " + name);

                    int increment = count.getAndIncrement();
//                    System.out.println("count: " + increment);
                }

                try {
                    Thread.sleep(10);
//                    3647
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).subscribe(new IOAsyncSubscribe<String>() {
            @Override
            public void call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("5: " + name);

                countDownLatch.countDown();
            }
        }, new RxAsyncError() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("total: " + count.get());
    }
}
