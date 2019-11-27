# Java 并发
> 记录 java 并发的一些示例

## java.util.concurrent 包下的主要类
> Atomic* 类通过关键字 volatile 和 CAS 算法实现操作的原子性

### AtomicBoolean
> 将 Boolean 类型的 true 和 false 转换成 Integer 类型的 1 和 0

### AtomicInteger
> 基本与上类似，多了些自增和自减的方法

