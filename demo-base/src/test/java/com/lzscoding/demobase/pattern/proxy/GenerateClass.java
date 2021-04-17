package com.lzscoding.demobase.pattern.proxy;

import com.lzscoding.demobase.pattern.proxy.dynamic.TeacherDao;
import org.springframework.cglib.core.DefaultGeneratorStrategy;
import org.springframework.cglib.proxy.Enhancer;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * 比较jdk动态代理和cglib动态代理生成字节码文件的异同
 * <p>
 * 根据生成的字节码文件
 * 可以看到cglib是直接继承了原有类，实现了Factory接口，
 * 而jdk是实现了自己的顶层接口，继承了Proxy接口。
 * 这里需要注意一下，这样的话，按照类来找话，jdk就找不到他的实现了，因为他的实现类实际上是一个Proxy类，而不是他自己。
 */
public class GenerateClass {

    private static String USER_SIR = System.getProperty("user.dir") + "\\file\\Proxy\\";

    public static void dynamicProxy() throws Exception {
        //byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces);
        // proxyName 为类名，interfaces为顶层接口Class
        byte[] dynamicProxyClassFile = ProxyGenerator.generateProxyClass("dynamicProxyName", new Class[]{TeacherDao.class});
        FileOutputStream fileOutputStream = new FileOutputStream(USER_SIR + "DynamicProxy.class");
        fileOutputStream.write(dynamicProxyClassFile);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 执行com.lzscoding.demobase.pattern.proxy.cglib.CglibProxyTest
     * 必须在实现cglib动态代理的代码之后，否则取不到DefaultGeneratorStrategy的实例。
     * 跟踪enhancer.create(); 这个代码可以看具体怎么生成字节码，怎么将字节码转化为类。过程基本雷同于jdk,主要区别在于生成字节码的区别。
     *
     * @param enhancer
     * @throws Exception
     */
    public static void cglibProxy(Enhancer enhancer) {
        byte[] bs = new byte[0];
        try {
            Thread.sleep(1000);
            bs = DefaultGeneratorStrategy.INSTANCE.generate(enhancer);
            FileOutputStream fileOutputStream = new FileOutputStream(USER_SIR + "CglibProxy.class");
            fileOutputStream.write(bs);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("请查看有无此文件" + USER_SIR + "CglibProxy.class");
        }

    }

    public static void main(String[] args) throws Exception {
        dynamicProxy();
    }
}
