package mobi.concurrent.atomic;

import mobi.common.Note;

import java.util.concurrent.atomic.AtomicIntegerArray;

@Note("测试 AtomicIntegerArray 类方法。原子性保证：通过 volatile 和 CAS 实现")
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        System.out.println(testIncrementAndGet());
    }

    @Note("初始化长度为 5，值均为 0 的数组，然后对第一个元素进行加一操作")
    private static AtomicIntegerArray testIncrementAndGet(){
        AtomicIntegerArray array = new AtomicIntegerArray(5);
        array.incrementAndGet(0);
        return array;
    }
}
