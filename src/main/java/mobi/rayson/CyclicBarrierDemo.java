package mobi.rayson;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-11-27
 *  Time: 6:10 PM
 *  Description: 循环栅栏。实现一组线程等待至某一状态后再全部执行其他的任务。
 *  羊圈：等待羊群都醒来集合 --> 打开羊圈门，释放羊 --> 关门，等待下次放羊
 **/
public class CyclicBarrierDemo {
  public static void main(String[] args) {
    int parties = 4;
    CyclicBarrier cyclicBarrier = new CyclicBarrier(parties);
    for (int i = 0; i < parties; i++) {
      new Writer(cyclicBarrier).start();
    }

    try {
      Thread.sleep(25000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("循环：即 CyclicBarrier 可以被重用。");
    for (int i = 0; i < parties; i++) {
      new Writer(cyclicBarrier).start();
    }
  }
}

class Writer extends Thread {

  private CyclicBarrier cyclicBarrier;

  Writer(CyclicBarrier cyclicBarrier) {
    this.cyclicBarrier = cyclicBarrier;
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + ": 正在写数据。");
    try {
      Thread.sleep(5000); // 模拟写入操作的耗时
      System.out.println(Thread.currentThread().getName() + ": 写入完成。");
      cyclicBarrier.await(); // 挂起当前线程，直到所有线程都达到此时的状态
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println("写任务执行完成，继续处理其他任务。");
  }
}