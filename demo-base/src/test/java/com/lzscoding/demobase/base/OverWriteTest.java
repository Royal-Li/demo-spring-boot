package com.lzscoding.demobase.base;

import java.util.*;

/*考察点1：重载静态多分派——根据传入重载方法的参数类型，选择更加合适的一个重载方法
考察点2：static方法不能被子类覆写，在子类中定义了和父类完全相同的static方法，则父类的static方法被隐藏，Son.staticmethod()或new Son().staticmethod()都是调用的子类的static方法，
         如果是Father.staticmethod()或者Father f = new Son(); f.staticmethod()调用的都是父类的static方法。
考察点3：此题如果都不是static方法，则最终的结果是A. 调用子类的getType，输出collection8*/
public class OverWriteTest {
    public static void main(String[] args) {
        Collection<?>[] collections =
                {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};
        Super subToSuper = new Sub();
        for (Collection<?> collection : collections) {
            System.out.println(subToSuper.getType(collection));
        }

        ArrayList<?>[] arrayLists =
                {new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>()};
        for (ArrayList<?> arrayList : arrayLists) {
            System.out.println(subToSuper.getType(arrayList));
        }
    }

    abstract static class Super {
        public static String getType(Collection<?> collection) {
            return "Super:collection";
        }

        public static String getType(List<?> list) {
            return "Super:list";
        }

        public String getType(ArrayList<?> list) {
            return "Super:arrayList";
        }

        public static String getType(Set<?> set) {
            return "Super:set";
        }

        public String getType(HashSet<?> set) {
            return "Super:hashSet";
        }
    }

    static class Sub extends Super {
        public static String getType(Collection<?> collection) {
            return "Sub";
        }

    }
}
