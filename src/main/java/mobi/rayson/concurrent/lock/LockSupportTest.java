package mobi.rayson.concurrent.lock;

import mobi.rayson.common.Note;

import java.util.concurrent.locks.LockSupport;

@Note("LockSupport")
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        testUnpark();
    }

    @Note("park: 主线程被阻塞，许可设为 0")
    private static void testPark() {
        System.out.println("开始");
        LockSupport.park();
        System.out.println("被主线程阻塞而不得执行 Blocking...");
    }

    @Note("unpark: 当许可恢复为 1 时，主线程继续执行")
    private static void testUnpark() {
        System.out.println("开始");
        LockSupport.unpark(Thread.currentThread());
        System.out.println("继续执行");
    }
}
