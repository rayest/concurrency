# Java 并发
> 记录 java 并发的一些示例

## java.util.concurrent 包下的主要类
> Atomic* 类通过关键字 volatile 和 CAS 算法实现操作的原子性
> CAS 算法都是在 unsafe 类中实现，该类调用 native 方法
> 通过反射以获取没有定义 getter 方法的变量 value 的值

### AtomicBoolean
> 将 Boolean 类型的 true 和 false 转换成 Integer 类型的 1 和 0

### AtomicInteger
> 基本与上述类似，多了些自增和自减的方法

### AtomicReference 
> 引用类型的原子操作。与基本类型的原子操作原理一致

### AtomicIntegerArray
> 针对数组中的每个元素进行原子操作，与上述类似
