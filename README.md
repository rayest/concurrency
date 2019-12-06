# Java 并发
> 记录 java 并发的一些示例

# java.util.concurrent 包下的主要类
1. Atomic* 类通过关键字 volatile 和 CAS 算法实现操作的原子性
2. CAS 算法都是在 unsafe 类中实现，该类调用 native 方法
3. 通过反射以获取没有定义 getter 方法的变量 value 的值

## Atomic*
### AtomicBoolean
> 将 Boolean 类型的 true 和 false 转换成 Integer 类型的 1 和 0

### AtomicInteger
> 基本与上述类似，多了些自增和自减的方法

### AtomicReference 
> 引用类型的原子操作。与基本类型的原子操作原理一致

### AtomicIntegerArray
> 针对数组中的每个元素进行原子操作，与上述类似


## lock
### LockSupport
1. LockSupport 类可以阻塞当前线程以及唤醒指定被阻塞的线程
2. 主要是通过 park() 和 unpark(thread) 方法来实现
3. 每个线程都有一个许可(permit)，permit 只有两个值 1 和 0，默认是 0


## 线程生命周期
> new Thread() --> start(runnable) --> running(待CPU分配时间片后) --> blocked --> terminated

## join
* 等待调用 join 方法的线程执行完，再继续余下的执行

## interrupt
* 在线程 t1 中调用线程 t2 的 interrupt 方法，即向 t2 发送中断消息，则 t2 设置为中断状态
* 而 t1 的 sleep、wait、join 方法会收到该中断消息，并抛出 InterruptedException 异常
* 自己可以打断自己
* 可以通过判断中断状态，优雅退出线程

## synchronized
* 对于多线程操作未受保护的共享变量，可能会因非原子操作而导致数据不一致
* 在进入锁时汇编指令 monitorenter，退出时 monitorexit
* 同步代码块 & 同步方法：锁的对象可能不同
* 锁：抢占。被同步的方法或者代码块，线程需要去抢占锁，抢到后自己独享，待自己不需要后释放掉，以供其他线程使用

## wait & notify 和 wait & notifyAll
* wait: 当前线程进入等待，直到其他线程通过 notify 或者 notifyAll 方式唤醒
* notify：一次只有一个线程可以被唤醒，然后该线程尝试去获得锁："object's monitor"
* notifyAll：唤醒所有在该 object's monitor 上等待的线程，然后这些线程中只有一个可以最终抢到锁 

## wait 与 sleep
* sleep：
> Thread.sleep()
> sleep 不会释放当前线程锁，线程会阻塞在该方法上。在睡眠指定时间后自动唤醒

* wait: 
> object.wait()
> wait 会释放当前线程会获取到的锁，且线程会被加入到请求该锁的等待队列中。需要被动唤醒

## 线程池
### 基本概念
* 任务队列(调度)
* 拒绝策略(性能), 当请求任务数量大于队列大小时，抛出异常、阻塞、直接丢弃、临时队列
* min(init)：初始大小
* max：允许JVM创建的最大线程数
* active：活跃的线程数

## double check
* 懒加载的单例模式会用到双重检查
> new instance() 并不是原子操作，是三步操作


## volatile
* 可见性、禁止重排序
* 内存可见性：主内存和本地内存
* 不保证原子性

## 原子性、可见性、有序性

## 可重入锁
* synchronized
> 可重入：通过对象计数器实现，每次请求计数器加一
> 程序执行完后自动释放锁
* ReentrantLock
> 可重入：通过内部的 state 变量记录锁请求次数
> 逐一释放所有的锁
* ReentrantReadWriteLock
> 与 ReentrantLock 同

## countDownLatch 和 CircleBarrier
> CountDownLatch与CyclicBarrier都是用于控制并发的工具类，
> 都可以理解成维护的就是一个计数器，但是这两者还是各有不同侧重点的：
* CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
* CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
* 调用CountDownLatch的countDown方法后，当前线程并不会阻塞，会继续往下执行
* 而调用CyclicBarrier的await方法，会阻塞当前线程，直到CyclicBarrier指定的线程全部都到达了指定点的时候，才能继续往下执行；
* CountDownLatch是不能复用的，而CyclicLatch是可以复用的。
