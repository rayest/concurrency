package mobi.rayson;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-04
 *  Time: 2:41 PM
 *  Description: 读写锁。
 *  读与读线程可交替执行，互不阻塞
 *  读写或者写写时，需要等到写操作完成，才能继续读或者继续写
 **/
public class ReentrantReadWriteLockApp {

  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(); // 默认为非公平的

  public static void main(String[] args) {
    ReentrantReadWriteLockApp reentrantReadWriteLockApp = new ReentrantReadWriteLockApp();
    new Thread(() -> reentrantReadWriteLockApp.read(Thread.currentThread())).start();
    new Thread(() -> reentrantReadWriteLockApp.read(Thread.currentThread())).start();
    new Thread(() -> reentrantReadWriteLockApp.write(Thread.currentThread())).start();
    new Thread(() -> reentrantReadWriteLockApp.write(Thread.currentThread())).start();
  }

  public void read(Thread thread) {
    lock.readLock().lock();
    try {
      long start = System.currentTimeMillis();
      while (System.currentTimeMillis() - start <= 5000) {
        System.out.println(thread.getName() + ": 读操作...");
        Thread.sleep(1000);
      }
      System.out.println(thread.getName() + ": 读操作完成。");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.readLock().unlock();
    }
  }

  public void write(Thread thread) {
    lock.writeLock().lock();
    try {
      long start = System.currentTimeMillis();
      while (System.currentTimeMillis() - start <= 5000) {
        System.out.println(thread.getName() + ": 写操作...");
        Thread.sleep(1000);
      }
      System.out.println(thread.getName() + ": 写操作完成。");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.writeLock().unlock();
    }
  }
}
