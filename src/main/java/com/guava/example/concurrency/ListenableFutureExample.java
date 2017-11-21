package com.guava.example.concurrency;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author majiabao on 2017/11/20.
 */
public class ListenableFutureExample {
    public static void main(String[] args) {

        //场景1
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = service.submit(new Callable<String>() {
            @Override
            public String call() {
                return "a";
            }
        });
        Futures.addCallback(explosion, new FutureCallback<String>() {
            // we want this handler to run immediately after we push the big red button!
            @Override
            public void onSuccess(String result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable thrown) {
                System.out.println("fail"); // escaped the explosion!
            }
        });
        //内部实现还是通过executorService来实现的
        //submit使用的是前一句创建的executor,添加回调又是使用的另外一个executor

        //场景2
        ListenableFutureTask task = ListenableFutureTask.create(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                return "a";
            }
        });

        //
    }
}
