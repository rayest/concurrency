package mobi.rayson.customerLockTest;

import java.util.ArrayList;
import java.util.Collection;

// TODO: 2019-12-05  自定义锁待完善
public class BooleanLock implements Lock {

    private boolean initValue; // false: 其他线程可以获得该锁

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    public BooleanLock(boolean initValue) {
        this.initValue = initValue;
    }

    @Override
    public void lock() {
        while (initValue){

        }
    }

    @Override
    public void lock(long mills) {

    }

    @Override
    public void unlock() {

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return null;
    }

    @Override
    public int getBlockedSize() {
        return 0;
    }
}
