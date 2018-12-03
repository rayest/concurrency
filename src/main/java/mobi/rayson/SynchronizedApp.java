package mobi.rayson;

import java.util.ArrayList;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-03
 *  Time: 9:45 PM
 *  Description: 加锁同步以确保线程安全
 *  资源：一个变量、一个对象、一个文件、一个数据库表等都可以作为一个资源
 *  被锁住的对象或者方法在同一时刻只能被一个线程访问或修改，其余的线程需要等待锁的释放，才能访问
 *  本例：两个线程依次插入数据，不会同时进行写操作
 **/
public class SynchronizedApp {

  public static void main(String[] args) {

    final SynchronizedRepository synchronizedRepository = new SynchronizedRepository();

    new Thread(() -> synchronizedRepository.insert(Thread.currentThread())).start();

    new Thread(() -> synchronizedRepository.insert(Thread.currentThread())).start();
  }
}

class SynchronizedRepository {

  private ArrayList<Integer> arrayList = new ArrayList<>();

  public synchronized void insert(Thread thread) {
    for (int i = 0; i < 5; i++) {
      try {
        System.out.println(thread.getName() + ": 正在插入数据..." + i);
        arrayList.add(i);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("插入了 " + arrayList.size() + " 条数据");
  }
}
