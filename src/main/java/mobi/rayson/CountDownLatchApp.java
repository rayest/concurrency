package mobi.rayson;

import java.util.concurrent.CountDownLatch;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-03
 *  Time: 11:23 AM
 *  Description: 二者比较相似
 *  CountDownLatch: 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。 like join ？？
 *  CyclicBarrier: N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
 **/
public class CountDownLatchApp {
  public static void main(String[] args) {
    int count = 2;
    // 申请两个门栓
    final CountDownLatch latch = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      // 带着两个门栓去请求
      new CountDownLatchReader(latch).start();
    }
  }
}

class CountDownLatchReader extends Thread {

  private CountDownLatch latch;

  CountDownLatchReader(CountDownLatch latch) {
    this.latch = latch;
  }

  public void run() {
    try {
      System.out.println(Thread.currentThread().getName() + ": 正在执行...");
      Thread.sleep(2000);
      System.out.println(Thread.currentThread().getName() + ": 执行完成。");
      // 每个线程执行完成，扔掉一个门栓，直到扔完为止，线程继续执行
      latch.countDown(); // 有一个任务想要往下执行，必须要等到其他的任务执行完毕后才可以继续往下执行
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "：继续执行其他任务...");
  }
}
