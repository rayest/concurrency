package mobi.concurrent;

import mobi.common.Note;
import mobi.common.PriorityArticle;

import java.util.concurrent.PriorityBlockingQueue;

@Note("PriorityBlockingQueue 有界阻塞队列-优先队列。需要实现 comparable 接口以定义优先条件")
public class PriorityBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue queue = testAdd();
        for (int i = 0; i < 3; i++) {
            PriorityArticle priorityArticle = (PriorityArticle) queue.take();
            System.out.println(priorityArticle.getId());
        }
    }

    private static PriorityBlockingQueue<PriorityArticle> testAdd() {
        PriorityBlockingQueue<PriorityArticle> queue = new PriorityBlockingQueue<>(3);
        queue.add(new PriorityArticle().setId(3).setName("leee"));
        queue.add(new PriorityArticle().setId(1).setName("le"));
        queue.add(new PriorityArticle().setId(2).setName("lee"));
        return queue;
    }
}
