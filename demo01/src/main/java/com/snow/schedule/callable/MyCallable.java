package com.snow.schedule.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author huangmengxue
 * @description
 * @date 2022/1/6 10:24
 */
public class MyCallable implements Callable<Integer> {
    private final int count;

    public MyCallable(int count) {
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {
        // 这里什么也不做 就返回count
        System.out.println(Thread.currentThread().getName());
        return count;
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 创建任务对象
            MyCallable myCallable = new MyCallable(i);
            // 提交到线程池
            Future<Integer> f = pool.submit(myCallable);
            // 获取返回结果
            list.add(f);
        }
        // 统计返回结果
        int result = 0;
        for (Future<Integer> i : list) {
            try {
                result = result + i.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
    }

}
