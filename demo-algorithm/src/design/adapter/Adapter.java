package design.adapter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.adapter
 * @descripation 接口不兼容而不能一起工作的那些类可以一起工作
 * @date 2021-07-16
 */
public class Adapter implements Runnable{
    private Callable callable;

    public Adapter(Callable callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        callable.call();
    }
}
