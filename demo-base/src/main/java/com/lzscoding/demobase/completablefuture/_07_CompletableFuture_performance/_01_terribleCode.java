package com.lzscoding.demobase.completablefuture._07_CompletableFuture_performance;

import com.lzscoding.demobase.completablefuture.SmallTool;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * 这是一个常见错误 当有一批异步任务时 一个一个执行 把异步变成了串行
 *
 */
public class _01_terribleCode {
    public static void main(String[] args) {

        SmallTool.printTimeAndThread("小白和小伙伴们 进餐厅点菜");
        long startTime = System.currentTimeMillis();

        ArrayList<Dish> dishes = new ArrayList<>();
        // 点菜
        for (int i = 1; i <= 10; i++) {
            Dish dish = new Dish("菜" + i, 1);
            dishes.add(dish);
        }
        // 做菜
        for (Dish dish : dishes) {
            CompletableFuture.runAsync(() -> dish.make()).join();
        }

        SmallTool.printTimeAndThread("菜都做好了，上桌 " + (System.currentTimeMillis() - startTime));

    }
}
