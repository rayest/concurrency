package mobi.rayson;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-06
 *  Time: 10:04 PM
 *  Description:
 *  yield 谦让的意思。即让出当前线程，使得其他线程可以执行。但并不能立即使得其他线程执行
 *  join 使得一个线程在另一个线程结束后再执行
 **/
public class YieldAndJoinApp {
  public static void main(String[] args) {
    Thread producer = new Producer();
    Thread consumer = new Consumer();
    Thread lazyPicker = new lazyPicker();
    producer.setPriority(Thread.MIN_PRIORITY);
    consumer.setPriority(Thread.MAX_PRIORITY);
    producer.start();
    consumer.start();
    try {
      producer.join();
      consumer.join();
      lazyPicker.start(); // 需要等到 producer 和 consumer 执行完成之后才能执行
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Producer extends Thread {
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread().getName() + ": 生产... " + i);
      Thread.yield();
    }
  }
}

class Consumer extends Thread {
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread().getName() + ": 消费... " + i);
      Thread.yield();
    }
  }
}

class lazyPicker extends Thread {
  public void run() {
    System.out.println(Thread.currentThread().getName() + ": 懒惰的捡拾者。");
  }
}
