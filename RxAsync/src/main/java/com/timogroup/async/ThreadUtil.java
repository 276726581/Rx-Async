package com.timogroup.async;

/**
 * Created by TimoRD on 2016/10/27.
 */
public final class ThreadUtil {

    private ThreadUtil() {

    }

    public static void showThreadName() {
        String groupName = Thread.currentThread().getThreadGroup().getName();
        String threadName = Thread.currentThread().getName();
        System.out.println(String.format("%s-%s", groupName, threadName));
    }

    public static void showThreadName(int num) {
        String groupName = Thread.currentThread().getThreadGroup().getName();
        String threadName = Thread.currentThread().getName();
        System.out.println(String.format("%d: %s-%s", num, groupName, threadName));
    }
}
