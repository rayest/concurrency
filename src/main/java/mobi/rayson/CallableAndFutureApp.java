package mobi.rayson;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-03
 *  Time: 3:58 PM
 *  Description:
 *  直接继承 Thread 或者实现 Runnable 接口执行完任务之后无法获取执行结果
 *  Callable 和 Future，可以在任务执行完毕之后得到任务执行结果
 **/
public class CallableAndFutureApp {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    CallableTask callableTask = new CallableTask();
    FutureTask<Integer> futureTask = new FutureTask<>(callableTask);
    executorService.submit(futureTask);
    executorService.shutdown();
    try {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + ": 继续执行...");
      System.out.println("结果: " + futureTask.get(5, TimeUnit.SECONDS));
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }
  }
}

class CallableTask implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println(Thread.currentThread().getName() + ": 开始计算...");
    Thread.sleep(3000);
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    return sum;
  }
}