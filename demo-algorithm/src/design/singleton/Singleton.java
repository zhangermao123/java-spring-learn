package design.singleton;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.singleton
 * @descripation 单例模式 饿汉
 * @date 2021-07-16
 */
public class Singleton {
    private String name;
    private static  final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance(){
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
