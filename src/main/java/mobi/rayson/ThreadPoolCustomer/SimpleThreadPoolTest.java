package mobi.rayson.ThreadPoolCustomer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

class SimpleThreadPool {
    private final int size;
    private static final int DEFAULT_SIZE = 10;
    private static volatile int seq = 0;
    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private static final ThreadGroup THREAD_GROUP = new ThreadGroup("Pool_Group");
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    private final static List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE); // 1. 创建线程池
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init(); // 2. 初始化线程池
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask(); // 3.创建工作任务
        }
    }

    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public void createWorkTask() {
        WorkTask workTask = new WorkTask(THREAD_GROUP, THREAD_PREFIX + (seq++));
        workTask.start();
        THREAD_QUEUE.add(workTask);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class WorkTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState() {
            return this.taskState;
        }

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                synchronized (TASK_QUEUE) {
                    // 如果队列为空即没有要执行的任务，则线程池等待
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUTER; // 有异常时直接退回到 OUTER 标签处
                        }
                    }

                    // 如果队列不为空，从队列中取出一个任务去执行
                    Runnable runnable = TASK_QUEUE.removeFirst();
                    if (runnable != null) {
                        taskState = TaskState.RUNNING;
                        runnable.run();
                        taskState = TaskState.FREE;
                    }
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }
}

public class SimpleThreadPoolTest {
    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool();
        IntStream.rangeClosed(0,40).forEach(i -> {
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
