package com.rayest.async;

import mobi.rayson.common.Note;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

public class CompletableFutureTest {

    private int selectOne() {
        return 1;
    }

    private int selectAnother() {
        return 2;
    }

    @Test
    @Note("supplyAsync(): supplier a function returning the value")
    public void test_supplyAsync() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::selectOne);
        assertEquals(1, (int) future.get());
    }

    @Test
    @Note("thenApply(): 接收前一步的结果作为参数进行计算。有返回值")
    public void test_thenApply() throws Exception {
        CompletableFuture<Integer> result = CompletableFuture
                .supplyAsync(this::selectOne)
                .thenApply(one -> one + 1);

        assertEquals(2, (int)result.get());
    }

    @Test
    @Note("thenAccept(): 接收上一阶段的输出作为本阶段的输入。无返回值")
    public void test_thenAccept() {
        CompletableFuture
                .supplyAsync(this::selectOne)
                .thenAccept(result -> System.out.println("result: " + result + 1));
    }

    @Test
    @Note("thenRun(): 不需要输入参数")
    public void test_thenRun() {
        CompletableFuture
                .supplyAsync(this::selectOne)
                .thenRun(() -> System.out.println("result:" + this.selectAnother()));
    }

    @Test
    @Note("thenCombine(): 整合两个计算结果")
    public void test_() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(this::selectOne)
                .thenCombine(CompletableFuture.completedFuture(this.selectAnother()), Integer::sum);
        assertEquals(3, (int)future.get());
    }

    @Test
    @Note("whenComplete(): 计算结果结束时做其他处理。异常等")
    public void test_whenComplete() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(this::selectOne)
                .whenComplete((result, e) -> System.out.println("result: " + result));

        assertEquals(1, (int)future.get());
    }
}
