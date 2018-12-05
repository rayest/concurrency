package mobi.rayson;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-05
 *  Time: 5:13 PM
 *  Description: Condition 是一个多线程间协调通信的工具类
 *  调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 **/
public class AwaitAndSignalApp {
  private int queueSize = 10;
  private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
  private Lock lock = new ReentrantLock();
  private Condition notEmpty = lock.newCondition();
  private Condition notFull = lock.newCondition();

  public static void main(String[] args) {
    AwaitAndSignalApp awaitAndSignalApp = new AwaitAndSignalApp();
    new Thread(awaitAndSignalApp::consume).start();
    new Thread(awaitAndSignalApp::produce).start();
  }

  private void produce() {
    while (true) {
      lock.lock();
      try {
        while (queue.size() == queueSize) {
          try {
            System.out.println("队列已满，等待有空余空间...");
            notFull.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        queue.offer(1); // 每次插入元素
        notEmpty.signal();
        System.out.println("向队列中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
      } finally {
        lock.unlock();
      }
    }
  }

  private void consume() {
    while (true) {
      lock.lock();
      try {
        while (queue.size() == 0) {
          try {
            System.out.println("队列为空，等待数据...");
            notEmpty.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        queue.poll(); // 每次移走队首元素
        notFull.signal();
        System.out.println("从队列中取走一个元素，队列剩余 " + queue.size() + " 个元素");
      } finally {
        lock.unlock();
      }
    }
  }
}
