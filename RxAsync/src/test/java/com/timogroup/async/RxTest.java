package com.timogroup.async;

import org.junit.Test;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by TimoRD on 2016/10/27.
 */
public class RxTest {

    @Test
    public void test() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(4);

        RxAsync.call(new RxCall<String>() {
            @Override
            public String call() throws Exception {
                ThreadUtil.showThreadName(1);
                return "test";
            }
        }, Schedulers.computation()).next(new RxNext<String, String>() {
            @Override
            public String call(String o) throws Exception {
                ThreadUtil.showThreadName(2);
                return o;
            }
        }, Schedulers.newThread()).next(new RxNext<String, String>() {
            @Override
            public String call(String o) throws Exception {
                ThreadUtil.showThreadName(3);
                return o;
            }
        }, Schedulers.io()).flatMap(new RxFlatMap<String, String>() {
            @Override
            public String[] call(String s) throws Exception {
                ThreadUtil.showThreadName(4);

                List<String> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add(s + i);
                }
                String[] array = list.toArray(new String[list.size()]);
                return array;
            }
        }, Schedulers.computation()).filter(new RxFilter<String>() {
            @Override
            public boolean accept(String o) throws Exception {
                ThreadUtil.showThreadName(5);

                if (!"test3".equals(o)) {
                    return true;
                }
                return false;
            }
        }, Schedulers.newThread()).subscribe(new RxSubscribe<String>() {
            @Override
            public void call(String s) {
                ThreadUtil.showThreadName(6);
                System.out.println(s);

                countDownLatch.countDown();
            }
        }, Schedulers.io());

        System.out.println("end");
        countDownLatch.await();
    }
}
