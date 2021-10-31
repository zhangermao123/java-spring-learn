package design.bridging;

import design.inter.Engine;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.bridging
 * @descripation TODO
 * @date 2021-07-16
 */
public class BossCar extends RefinedCar{

    public BossCar(Engine engine) {
        super(engine);
    }

    @Override
    String getBrand() {
        return "boss car";
    }
}
