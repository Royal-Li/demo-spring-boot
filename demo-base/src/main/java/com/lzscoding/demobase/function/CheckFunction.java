package com.lzscoding.demobase.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * (检查)抛出lambda中异常情况
 *
 * 传入方法 检查方法结果是否为空
 * @createDate 2021/4/21
 */
public interface CheckFunction {

    Logger log = LoggerFactory.getLogger(CheckFunction.class);

    interface Function<T, R> {

        /**
         * Function类型
         *
         * @param function 处理函数
         * @param p        入参
         * @return 处理结果, 报错返回 Optional.empty();
         * @author lzs
         * @createDate 2021/4/21
         */
        static <T, R> Optional<R> tryOf(Function<T, R> function, T p) {
            try {
                return Optional.ofNullable(function.apply(p));
            } catch (Throwable t) {
                log.error("{} 抛出异常,入参数据:{}", function, p, t);
                return Optional.empty();
            }
        }

        /**
         * Function类型
         *
         * @param function 处理函数
         * @param p        入参
         * @param errorFunction 发生异常处理函数
         * @param errorP   异常函数入参
         * @return 处理结果, 报错返回 errorFunction 的返回值;
         * @author lzs
         * @createDate 2021/4/21
         */
        static <T,R,A> Optional<R> tryOf(Function<T, R> function, T p, Function<A, R> errorFunction, A errorP){
            try {
                return Optional.ofNullable(function.apply(p));
            } catch (Throwable t) {
                log.error("{} 抛出异常,入参数据:{} 异常函数{}  异常函数入参{}", function, p, errorFunction,errorP,t);
                return tryOf(errorFunction,errorP);
            }
        }

        /**
         * 参照 java.util.function.Function
         *
         * @param t 入参
         * @return R 类型数据
         * @throws Exception 异常
         * @author lzs
         * @createDate 2021/4/21
         */
        R apply(T t) throws Exception;

    }

    interface Supplier<T> {

        /**
         * Supplier 类型
         *
         * @param supplier 处理函数
         * @return 返回语句的返回值(optional), 报错则为Optional.empty()
         * @author lzs
         */
        static <T> Optional<T> tryOf(CheckFunction.Supplier<? extends T> supplier) {
            try {
                return Optional.ofNullable(supplier.get());
            } catch (Throwable t) {
                log.error("{} 抛出异常", supplier, t);
                return Optional.empty();
            }
        }

        /**
         * 参照 java.util.function.Supplier
         *
         * @return T 类型
         * @throws Exception 异常
         * @author lzs
         * @createDate 2021/4/21
         */
        T get() throws Exception;

    }

    interface Consumer<T> {

        /**
         * consumer 类型
         * <p> 报错将忽略
         *
         * @param consumer 处理函数
         * @param p        入参
         * @author lzs
         * @createDate 2021/4/21
         */
        static <T> void tryOf(CheckFunction.Consumer<T> consumer, T p) {
            try {
                consumer.accept(p);
            } catch (Throwable t) {
                log.error("{} 抛出异常,入参数据:{}", consumer, p, t);
            }
        }

        /**
         * consumer 类型
         * <p> 报错将忽略
         *
         * @param consumer 处理函数
         * @param p        入参
         * @param errorConsumer 异常处理函数
         * @param errorP        异常函数入参
         * @author lzs
         * @createDate 2021/4/21
         */
        static <T,A> void tryOf(CheckFunction.Consumer<T> consumer, T p, CheckFunction.Consumer<A> errorConsumer, A errorP) {
            try {
                consumer.accept(p);
            } catch (Throwable t) {
                log.error("{} 抛出异常,入参数据:{}   异常函数{}  异常函数入参{}", consumer, p,errorConsumer,errorP, t);
                tryOf(errorConsumer,errorP);
            }
        }


        /**
         * 参照 java.util.function.Consumer
         *
         * @param t 入参
         * @throws Exception 异常
         * @author lzs
         * @createDate 2021/4/21
         */
        void accept(T t) throws Exception;

    }

    interface Predicate<T> {

        /**
         * Predicate类型
         *
         * @param predicate 处理函数
         * @param p         入参
         * @return 处理结果, 报错返回 false
         * @author lzs
         * @createDate 2021/4/21
         */
        static <T> boolean tryOf(CheckFunction.Predicate<T> predicate, T p) {
            try {
                return predicate.test(p);
            } catch (Throwable t) {
                log.error("{} 抛出异常,入参数据:{}", predicate, p, t);
                return false;
            }
        }

        /**
         * Predicate类型
         *
         * @param predicate 处理函数
         * @param p         入参
         * @param errorPredicate 异常处理函数
         * @param errorP         异常函数入参
         * @return 处理结果, 报错返回 false
         * @author lzs
         * @createDate 2021/4/21
         */
        static <T,A> boolean tryOf(CheckFunction.Predicate<T> predicate, T p, CheckFunction.Predicate<A> errorPredicate, A errorP) {
            try {
                return predicate.test(p);
            } catch (Throwable t) {
                log.error("{} 抛出异常,入参数据:{}  异常函数{}  异常函数入参{}", predicate, p,errorPredicate,errorP, t);
                return tryOf(errorPredicate,errorP);
            }
        }

        /**
         * 参照 java.util.function.Predicate
         *
         * @param t 入参
         * @return boolean
         * @throws Exception 异常
         * @author lzs
         * @createDate 2021/4/21
         */
        boolean test(T t) throws Exception;


    }

}

