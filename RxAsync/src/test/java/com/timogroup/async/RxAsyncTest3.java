package com.timogroup.async;

import com.timogroup.async.callback.call.NewThreadAsyncCall;
import com.timogroup.async.callback.map.IOAsyncMap;
import com.timogroup.async.callback.subscribe.IOAsyncSubscribe;
import com.timogroup.async.callback.subscribe.RxAsyncError;

import java.util.concurrent.CountDownLatch;

/**
 * Created by TimoRD on 2016/9/11.
 */
public class RxAsyncTest3 {

    public static void main(String[] args) {
        int size = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        RxAsync.call(new NewThreadAsyncCall<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                System.out.println("1: " + name);

                StringBuffer buffer = new StringBuffer();
                for (long i = 0; i < size; i++) {
                    buffer.append(i).append(",");
                }
                buffer.deleteCharAt(buffer.length() - 1);
                String s = buffer.toString();
                return s;
            }
        }).map(new IOAsyncMap<String, String>() {
            @Override
            public String[] call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("2: " + name);

                String[] split = s.split(",");
                return split;
            }
        }).subscribe(new IOAsyncSubscribe<String>() {
            @Override
            public void call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("3: " + name);

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
    }
}
