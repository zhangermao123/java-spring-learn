package design.adapter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.adapter
 * @descripation TODO
 * @date 2021-07-16
 */
public class Task implements Callable{

    private long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public double call() {
        double rad = Math.random()*num;
        System.out.println("task random is : "+ rad);
        return rad;
    }
}
