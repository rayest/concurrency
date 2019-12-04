package mobi.concurrent.atomic;

import mobi.common.Note;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

@Note("测试 Java-util-concurrent 包下的 AtomicBoolean 类方法")
public class AtomicBooleanTest {
    public static void main(String[] args) {
        System.out.println(testGetAndSet());
    }

    @Note("测试 get 方法。返回为 true")
    private static boolean testGetTrue() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        return atomicBoolean.get();
    }

    @Note("通过反射获取变量 value 值。当 true 时，value 为 1")
    private static int testGetValueWhenTrue() throws Exception {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);

        Class c = Class.forName("java.util.concurrent.atomic.AtomicBoolean");
        Field field = c.getDeclaredField("value");
        field.setAccessible(true);

        return (int) field.get(atomicBoolean);
    }

    @Note("通过反射获取变量 value 值。当 false 或者默认时，value 为 0")
    private static int testGetValueWhenFalseOrDefault() throws Exception {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        Class c = Class.forName("java.util.concurrent.atomic.AtomicBoolean");
        Field field = c.getDeclaredField("value");
        field.setAccessible(true);

        return (int) field.get(atomicBoolean);
    }

    @Note("测试 getAndSet 方法。先获取旧值，再通过 CAS 算法更新为新值。但返回为旧值")
    private static boolean testGetAndSet() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return atomicBoolean.getAndSet(true);
    }

    @Note("测试 set 方法。通过 get 获取。返回 true")
    private static boolean testSet() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.set(true);
        return atomicBoolean.get();
    }
}
