package com.guava.example.concurrency;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author majiabao on 2017/11/20.
 */
public class ListenableFutureExample {
    public static void main(String[] args) {

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> explosion = service.submit(new Callable<String>() {
            @Override
            public String call() {
                System.out.println("a");
                return "a";
            }
        });
        //submit会触发run操作
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
        //addcallback会触发run操作
        //内部实现还是通过executorService来实现的
        //submit使用的是前一句创建的executor,添加回调又是使用的另外一个executor
        //在addCallback执行的过程中，会调用future.get方法，同步来获取值，但不会阻塞主线程,只会阻塞自己那个线程，所以还是异步的


        //2.transform
        ListenableFuture<String> transform = Futures.transform(explosion, new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                String result =  "transfer" + input;
                System.out.println(result);
                return result;
            }
        }, MoreExecutors.directExecutor());
        //和callback逻辑相似，将explosion和function的操作封装成一个runnable整体，然后将最终的执行结果设置到结果中。
        // 同样也是在transform的执行过程中，会调future.get
    }
}
