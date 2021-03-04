package com.lzscoding.demobase.java8;

import com.lzscoding.demobase.domain.User;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口:Supplier Funtion Consume Perdicate
 * jdk8之前通过匿名内部类实现函数式接口
 * jdk8之后可以使用lambda表达式来实现,lambda表达式就是为了优化匿名内部类而生
 */
public class FunctionInterfaceTest {
    /*@FunctionalInterface
    1、该注解只能标记在”有且仅有一个抽象方法”的接口上。
    2、JDK8接口中的静态方法和默认方法，都不算是抽象方法。
    3、接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
    4、该注解不是必须的，如果一个接口符合”函数式接口”定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。*/

    /**
     * 供给型接口
     * Supplier<T>
     * 没有输入,返回一个对象T
     */
    public static void SupperlierTest() {
        Supplier<String> supplier = () -> "hello word";
        System.out.println(supplier.get());
        Supplier<User> supplier2 = () -> new User();
        System.out.println(supplier2.get());
    }

    /**
     * 功能性函数接口
     * Function<T, R>
     * 对象转换，T->R
     */
    public static void FuntionTest() {
        Function<Long, String> function = str -> str.toString();
        System.out.println(function.apply(999L) + function.apply(999L).getClass());
        Function<String, Long> function1 = str1 -> Long.valueOf(str1);
        //andThen 执行funtion1之后再执行function2
        System.out.println(function1.andThen(function).apply("123456") + function1.andThen(function).apply("123456").getClass());
    }

    /**
     * 消费型接口
     * Consumer<T>
     * 改变对象T内部属性的值
     */
    public static void ConsumerTest() {
        User userConsumer = new User();
        BiConsumer<User, String> setNameConsumer = User::setName;
        //BiConsumer<User, String> setNameConsumer = userConsumer::setName;
        setNameConsumer.accept(userConsumer,"China");
        System.out.println(userConsumer);
    }

    /**
     * 断言型接口
     * Predicate<T>
     * 进行逻辑判断,返回boolean值
     * Predicate常用于集合的过滤，得到一个新的集合
     * Stream filter(Predicate<? super T> predicate);
     */
    public static void PredicateTest() {
        Predicate<Integer> predicate = age -> age > 18;
    }


    public static void main(String[] args) {
        SupperlierTest();
        FuntionTest();
        ConsumerTest();
    }
}
