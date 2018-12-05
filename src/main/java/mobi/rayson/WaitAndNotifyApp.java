package mobi.rayson;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-05
 *  Time: 4:27 PM
 *  Description: object 对象的 wait 和 notify 方法
 **/
public class WaitAndNotifyApp {
  private static Object object = new Object();

  public static void main(String[] args) {
    new WaitThread(object).start();
    new NotifyThread(object).start();
  }
}

class WaitThread extends Thread {

  private final Object object;

  WaitThread(Object object) {
    this.object = object;
  }

  public void run() {
    synchronized (object) {
      try {
        System.out.println(Thread.currentThread().getName() + ": 开始执行业务...");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ": 执行了部分业务，开始进入等待...");
        object.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + ": 重新获得了锁，继续未完成的大业...");
    }
  }
}

class NotifyThread extends Thread {

  private final Object object;

  NotifyThread(Object object) {
    this.object = object;
  }

  public void run() {
    synchronized (object) {
      // 一个线程被唤醒不代表立即获取了对象的monitor，只有等调用完notify()并退出synchronized块，释放对象锁后，其余线程才可获得锁执行。
      try {
        System.out.println(Thread.currentThread().getName() + ": 执行业务...");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ": 执行完成，即将调用 notify()");
        object.notify();
        System.out.println(Thread.currentThread().getName() + ": 调用了 notify()");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Thread.currentThread().getName() + "：退出同步代码块，释放了锁");
  }
}