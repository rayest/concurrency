package mobi.rayson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-03
 *  Time: 2:44 PM
 *  Description: 比较 Semaphore
 *  Semaphore：创建了多少线程，实际就会有多少线程进行执行，只是可同时执行的线程数量会受到限制
 *  线程池：执行任务的始终是两条工作线程，这两条工作线程不是我们自己创建的，是线程池提供的
 **/
public class SemaphoreAndThreadPoolApp {
  public static void main(String[] args) {
    int corePoolSize = 2;
    int maxPoolSize = 5;
    long keepAliveTime = 0L;
    ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
        keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

    for (int i = 0; i < 5; i++) {
      Thread thread = new Thread(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + ": 开始工作...");
          Thread.sleep(3000);
          System.out.println(Thread.currentThread().getName() + ": 工作结束。");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });

      executorService.submit(thread);
    }
    executorService.shutdown();
  }
}
