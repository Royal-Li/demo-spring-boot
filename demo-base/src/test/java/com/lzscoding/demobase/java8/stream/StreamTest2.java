package com.lzscoding.demobase.java8.stream;



import com.lzscoding.demobase.domain.User;
import com.lzscoding.demobase.enums.GenderEnum;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 {

    /**
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
    public static void test1() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
        System.out.println("------------------------------------");
    }

    /**
     * 规约  reduce
     * 将流缩减成一个值 实现对集合求和,求乘积,求最值
     */
    public static void test2() {
        List<Integer> list = Arrays.asList(3, 11, 2, 8, 4, 1);
        //第一种求和方式
        Optional<Integer> sum1 = list.stream().reduce(Integer::sum);
        //第二种求和方式
        Optional<Integer> sum2 = list.stream().reduce((x, y) -> x + y);
        //第三种求和方式
        Integer sum3 = list.stream().reduce(0, Integer::sum);
        //求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> {
            x = x * 10; //这里我做了点小手脚
            return x * y;
        });
        //求最值
        Optional<Integer> max1 = list.stream().reduce(Integer::max);
        //求最值
        Optional<Integer> max2 = list.stream().reduce((x, y) -> x > y ? x : y);

        System.out.println("list求和: " + sum1 + "," + sum2 + "," + sum3);
        System.out.println("list求乘积: " + product);
        System.out.println("list求最大值: " + max1 + "," + max2);


        List<Integer> newList = Arrays.asList(1);
        //Arrays.asList没有复写add/remove/clear 直接调用AbstractList中的add方法 这里直接会抛出一个UnsupportedOperationException的异常 所以要再包装一次 转为真正util下面的ArrayList
        ArrayList arrayList = new ArrayList<>(newList);

        ArrayList<Integer> accResult_ = Stream.of(2, 3, 4)
                .reduce(arrayList,
                        (acc, item) -> {
                            acc.add(item);
                            System.out.print("item: " + item + "\t");
                            System.out.print("acc+ : " + acc + "\t");
                            System.out.println("BiFunction");
                            return acc;
                        }, (acc, item) -> null);
        System.out.println("newList: " + newList);
        System.out.println("arrayList: " + arrayList);
        System.out.println("accResult_: " + accResult_);
        List<Integer> collect = Stream.of(100, 200, 700).collect(Collectors.toList());
        System.out.println("collect: " + collect);

        //通过运行结果可以看出，第三个参数定义的规则并没有执行。
        // 这是因为reduce的第三个参数是在使用parallelStream的reduce操作时，合并各个流结果的，本例中使用的是stream，所以第三个参数是不起作用的。
        // 上述示例，提供一个只有一个元素1的arrayList，通过累加器迭代，将stream中的数据添加到arrayList中
    }

    /**
     * 规约  reduce
     * 将流缩减成一个值 实现对实体集合 求解
     */
    public static void test3() {
        System.out.println("------------------------------------");
        List<User> userList = new ArrayList<>();
        userList.add(new User(8L, "hamasa", 88, "88.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(1L, "tom", 111, "11.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(6L, "jason", 66, "66.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(5L, "peter", 55, "55.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(2L, "jack", 22, "22.@qq.com", GenderEnum.Male, new Date()));
        userList.add(new User(7L, "wali", 88, "77.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(3L, "jerry", 33, "33.@qq.com", GenderEnum.Female, new Date()));
        userList.add(new User(4L, "lisaliha", 44, "44.@qq.com", GenderEnum.Female, new Date()));
        //年龄之和1
        Optional<Integer> sumAge1 = userList.stream().map(User::getAge).reduce(Integer::sum);
        //年龄之和2   这段说实话没看懂 ->> 后来理解  先确定入参出参(第二个参数) 再确定操作(第三个参数) 第三个参数只有在parallelStream时候生效
        // reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
        Integer sumAge2 = userList.stream().reduce(0, (sum, x) -> sum += x.getAge(), (sum1, sum2) -> sum1 + sum2);
        //年龄之和3
        Integer sumAge3 = userList.parallelStream().reduce(0, (sum, u) -> sum += u.getAge(), Integer::sum);
        System.out.println("年龄之和:" + sumAge1+"---"+sumAge2+"---"+sumAge3);

        //名字拼接
        String nameString1 = userList.parallelStream().reduce("名字拼接:", (name, u) -> name += u.getName(), (user1, user2) -> user1 + user2);
        String nameString2 = userList.stream().reduce("名字拼接:", (name, u) -> name += u.getName(), (user1, user2) -> "不是parallelStream 我不生效");
        //名字最长
        Optional<User> nameMax = userList.stream().reduce((user1, user2) -> user1.getName().length() > user2.getName().length() ? user1 : user2);
        System.out.println(nameString1 + "\n" + nameString2 + "\n名字最长:" + nameMax.get());

        //年龄最大1
        Integer oldMax = userList.stream().reduce(0, (max, u) -> max > u.getAge() ? max : u.getAge(), Integer::max);
        Optional<User> oldMax2 = userList.stream().max(Comparator.comparing(User::getAge));
        System.out.println("年量最大:" + oldMax + "," + oldMax2.get().getAge());

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }


}
