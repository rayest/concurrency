package mobi.rayson.concurrent.atomic;

import mobi.rayson.common.Article;
import mobi.rayson.common.Note;

import java.util.concurrent.atomic.AtomicReference;

@Note("测试 AtomicReference 类方法。原子性保证：通过 volatile 和 CAS 实现")
public class AtomicReferenceTest {
    public static void main(String[] args) {
        System.out.println(testSet());
    }

    @Note("引用类型的原子操作，类似于基本类型。get 操作")
    private static Article testGet() {
        Article article = new Article().setId(1).setName("lee");
        AtomicReference<Article> atomicReference = new AtomicReference<>(article);
        return atomicReference.get();
    }

    @Note("引用类型的原子操作，类似于基本类型。set 操作")
    private static Article testSet() {
        Article oldValue = new Article().setId(1).setName("lee");
        AtomicReference<Article> atomicReference = new AtomicReference<>(oldValue);
        Article newValue = new Article().setId(1).setName("rayest");
        atomicReference.set(newValue);
        return atomicReference.get();
    }
}
