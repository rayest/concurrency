package mobi.concurrent;

import mobi.common.Article;
import mobi.common.Note;

import java.util.concurrent.ArrayBlockingQueue;

@Note("ArrayBlockingQueue 有界阻塞队列-数组")
public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        System.out.println(testAdd());
    }

    private static ArrayBlockingQueue<Article> testAdd(){
        ArrayBlockingQueue<Article> queue = new ArrayBlockingQueue<>(2);
        queue.add(new Article().setId(1).setName("le"));
        queue.add(new Article().setId(2).setName("lee"));
        return queue;
    }
}
