package com.lzscoding.demobase.pattern.proxy.staticproxy;

/**
 * 代理对象
 * 静态代理
 * 优点 在不修改目标对象功能前提下,能通过代理对象对目标功能扩展
 * 缺点 因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类
 *      一旦接口增加方法,目标对象和代理对象都需要维护
 */
public class TeacherDaoProxy implements TeacherDao {

    //目标对象通过接口来聚合
    private TeacherDao target;

    //构造器
    public TeacherDaoProxy(TeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("代理开始.....");
        target.teach();
        System.out.println("代理提交.....");
    }
}
