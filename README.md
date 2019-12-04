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
