package mobi.rayson;

import java.util.concurrent.Semaphore;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-03
 *  Time: 2:09 PM
 *  Description: 信号量机制
 *  用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源。
 **/
public class SemaphoreApp {
  public static void main(String[] args) {
    int workers = 8; // 8 个工人
    int permits = 5; // 5 个许可，即5个线程为最大工作线程
    Semaphore semaphore = new Semaphore(permits);
    for (int i = 0; i < workers; i++) {
      new SemaphoreWorker(i, semaphore).start();
    }
  }
}

class SemaphoreWorker extends Thread {

  private int num;
  private Semaphore semaphore;

  SemaphoreWorker(int num, Semaphore semaphore) {
    this.num = num;
    this.semaphore = semaphore;
  }

  @Override
  public void run() {
    try {
      semaphore.acquire(); // 访问特定资源前，需要获得许可，如果许可数量为0，该线程则一直阻塞，直到有可用许可。
      System.out.println("工人: " + num + " 开始工作...");
      Thread.sleep(2000);
      semaphore.release(); // 访问资源后，使用release释放许可。即线程
      System.out.println("工人: " + num + " 工作结束，释放机器。");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
