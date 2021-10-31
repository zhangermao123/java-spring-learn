package design.bridging;

import design.inter.Engine;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.bridging
 * @descripation TODO
 * @date 2021-07-16
 */
public class HybridEngine implements Engine {

    @Override
    public void start() {
        System.out.println("HybridEngine is start");
    }
}
