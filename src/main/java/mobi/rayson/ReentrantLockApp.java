package mobi.rayson;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-04
 *  Time: 1:44 PM
 *  Description: 可重入锁。指的是在一个线程中可以多次获取同一把锁，以避免死锁
 *  比如：一个线程在执行一个带锁的方法，该方法中又调用了另一个需要相同锁的方法，则该线程可以直接执行调用的方法，而无需重新获得锁；
 *  synchronized 与 ReentrantLock 都是可重入锁
 **/
public class ReentrantLockApp {

  private ArrayList<Integer> arrayList = new ArrayList<>();

  private Lock lock = new ReentrantLock(); // 需要申明为全局变量，以使得两个线程请求的是同一把锁

  public static void main(String[] args) {
    ReentrantLockApp reentrantLockApp = new ReentrantLockApp();
    new Thread(() -> reentrantLockApp.insert(Thread.currentThread())).start();
    new Thread(() -> reentrantLockApp.insert(Thread.currentThread())).start();
  }

  private void insert(Thread thread) {
    lock.lock(); // or you can do with lock.tryLock();
    try {
      System.out.println(thread.getName() + ": 获得了锁, 执行该线程的任务...");
      for (int i = 0; i < 5; i++) {
        Thread.sleep(400);
        arrayList.add(i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println(thread.getName() + ": 任务执行完成，释放了锁。");
      lock.unlock();
    }
    System.out.println("结果：" + arrayList.size());
  }
}
