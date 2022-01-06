package com.snow.schedule.runnable;

import lombok.SneakyThrows;

/**
 * @author huangmengxue
 * @description
 * @date 2022/1/6 9:54
 */
public class MyRunnable implements Runnable {
    int i = 10;

    @SneakyThrows
    @Override
    public void run() {
        while (i > 0) {
            i = i - 1;
            System.out.println(Thread.currentThread().getName() + ":i=" + i);
            Thread.sleep(6000);
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread01 = new Thread(myRunnable);
        thread01.start();
        Thread thread02 = new Thread(myRunnable);
        thread02.start();
    }

}
