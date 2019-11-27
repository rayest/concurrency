package mobi.concurrent;

import mobi.common.Note;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

@Note("测试 AtomicInteger 类方法。原子性保证：通过 volatile 和 CAS 实现")
public class AtomicIntegerTest {
    public static void main(String[] args) {
        System.out.println(testDecrementAndGet());
    }

    @Note("旧值增加一，然后返回旧值")
    private static int testGetAndIncrement() {
        AtomicInteger atomicInteger = new AtomicInteger();
        return atomicInteger.getAndIncrement();
    }

    @Note("旧值增加一，然后返回新值。替代某些情况下的 i++ 操作")
    private static int testIncrementAndGet() {
        AtomicInteger atomicInteger = new AtomicInteger();
        return atomicInteger.incrementAndGet();
    }

    @Note("相加。返回加后的结果")
    private static int testAddAndGet() {
        AtomicInteger atomicInteger = new AtomicInteger();
        return atomicInteger.addAndGet(10);
    }

    @Note("相减。返回减后的结果")
    private static int testDecrementAndGet() {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        return atomicInteger.decrementAndGet();
    }
}
