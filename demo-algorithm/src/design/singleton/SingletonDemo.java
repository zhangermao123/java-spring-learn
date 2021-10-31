package design.singleton;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.singleton
 * @descripation 静态内部类实现，懒加载，线程安全
 * @date 2021-10-07
 */
public class SingletonDemo {
    private static class SingletonInstance{
        private static final SingletonDemo instance = new SingletonDemo();
    }

    private SingletonDemo() {}

    public static SingletonDemo getInstance(){
        return SingletonInstance.instance;
    }
}
