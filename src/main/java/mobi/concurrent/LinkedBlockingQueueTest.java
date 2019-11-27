package mobi.concurrent;

import mobi.common.Article;
import mobi.common.Note;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Note("LinkedBlockingQueue 有界阻塞队列-链表")
public class LinkedBlockingQueueTest {
    public static void main(String[] args) {
        System.out.println(testAdd());
    }

    private static LinkedBlockingQueue<Article> testAdd(){
        LinkedBlockingQueue<Article> queue = new LinkedBlockingQueue<>(2);
        queue.add(new Article().setId(1).setName("le"));
        queue.add(new Article().setId(2).setName("lee"));
        return queue;
    }
}
