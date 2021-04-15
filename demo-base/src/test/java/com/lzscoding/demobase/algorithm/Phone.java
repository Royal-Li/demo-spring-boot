package com.lzscoding.demobase.algorithm;

class Phone {
    int price;
}

class Main {
    public static void main(String[] args) {
        Phone one = new Phone();
        one.price = 1000;

        changePrice(one);
        System.out.println(one + "----" + one.price);
    }

    public static void changePrice(Phone two) {
        //two = new Phone();
        two.price = 2000;
        System.out.println(two + "----" + two.price);
    }
}

