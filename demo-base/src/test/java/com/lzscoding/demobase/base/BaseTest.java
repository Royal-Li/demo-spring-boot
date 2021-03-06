package com.lzscoding.demobase.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

@Slf4j
public class BaseTest {

    String str = new String("tarena");
    char[] ch = {'a', 'b', 'c'};

    @Test
    public void test1() {
        System.out.println(13 & 17);
        String str = "";
        System.out.println(str.split(",").length);
    }


    public void test2(String str, char ch[]) {
//        引用类型变量，传递的是地址，属于引用传递。
        str = "test ok";
        ch[0] = 'g';
    }

    @Test
    public void test3() {
//        java 黙认浮点类型为double
        Object obj1 = (short) 10 / 10.2 * 2;
        Object obj2 = (short) (10 / 10.2 * 2);
        System.out.println(obj1.getClass().toString());
        System.out.println(obj2.getClass().toString());
    }

    @Test
    public void test4() {
        /*计算机本身储存的就是补码：
        正数的原码补码反码都是自身
        那么10的补码就是10的原码：0000 0000 0000 1010——这是补码，因为现在是计算机在计算
        ~10的补码就是：1111 1111 1111 0101
        ~10的反码就是：1111 1111 1111 0100——补码减1
        ~10的原码就是：1000 0000 0000 1011——反码取反：这个才是正常二进制数，换算为整数为-11
        原码才可以对应为正常的整数，补码只有转换为原码才能被正常人类识别。*/
        int i = 5;
        int j = 10;
        System.out.println(i + ~j);
    }

    @Test
    public void test5() {
        char myChar = 'g';
        String myStr = Character.toString(myChar);
        System.out.println("String is: " + myStr);
        myStr = String.valueOf(myChar);
        System.out.println("String is: " + myStr);
    }

    private static int x = 100;

    @Test
    public void test6() {
        BaseTest hs1 = new BaseTest();
        hs1.x++;
        BaseTest hs2 = new BaseTest();
        hs2.x++;
        hs1 = new BaseTest();
        hs1.x++;
        BaseTest.x--;
        System.out.println("x=" + x);
    }

    @Test
    public void test7() {
        System.out.println("------------------------------");
        //   << 16 表示左移，如果该数为正，则高位补0，若为负数，则高位补1；
        long prefix = System.currentTimeMillis() << 16;
        System.out.println(prefix + "-----" + System.currentTimeMillis());
        System.out.println(prefix / System.currentTimeMillis());
        System.out.println("转成二进制--->>" + Integer.toBinaryString((int) (prefix / System.currentTimeMillis())));
    }

    /**
     * 验证 &&和& ||和|
     * 的区别
     */
    @Test
    public void test8() {
        System.out.println("------------------------------");

        int i = 0;
        if (false && i++ == 1) {
            System.out.println("&& 有一方错了进不来");
        } else {
            System.out.println("&& 有一方错了 i++ 不会执行 i = " + i);
        }
        if (false & i++ == 1) {
            System.out.println("& 有一方错了进不来");
        } else {
            System.out.println("& 有一方错了 i++ 会执行 i = " + i);
        }
        i = 0;
        if (true || i++ == 100) {
            System.out.println("|| 有一方对了能进来 i++不执行 i = " + i);
        } else {
            System.out.println("|| 有一方对了进不来");
        }
        if (true | i++ == 100) {
            System.out.println("| 有一方对了能进来 i++ 会执行 i = " + i);
        } else {
            System.out.println("| 有一方对了进不来");
        }

    }

    public static void test9() {
        IntStream.range(1, 10).forEach(i -> {
            try {
                System.out.println(i);
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        test9();
        BaseTest ex = new BaseTest();
        System.out.println(ex.str + " and " + ex.ch);
        ex.test2(ex.str, ex.ch);
        System.out.println(ex.str + " and " + ex.ch);

    }
}
