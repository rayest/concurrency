package mobi.rayson;

/***
 *  Created with IntelliJ IDEA.
 *  User:  lirui
 *  Date:  2018-12-04
 *  Time: 6:02 PM
 *  Description: ThreadLocal 并不是一个 Thread，而是 Thread 的局部变量
 *  使用 ThreadLocal 维护变量时，ThreadLocal 为每个使用该变量的线程提供独立的变量副本
 *  所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 **/
class ThreadLocalApp {
  public static void main(String[] args) {
    SequenceNumber t1 = new SequenceNumber();
    SequenceNumber t2 = new SequenceNumber();
    SequenceNumber t3 = new SequenceNumber();
    t1.start();
    t2.start();
    t3.start();
  }
}

class SequenceNumber extends Thread {

  // 覆盖初始化方法, 创建 ThreadLocal 的变量
  private static ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

  public int getNextNum() {
    seqNum.set(seqNum.get() + 1);
    return seqNum.get();
  }

  // 线程产生序列号
  public void run() {
    for (int i = 0; i < 3; i++) {
      System.out.println(Thread.currentThread().getName() + " sn: " + getNextNum());
    }
  }
}