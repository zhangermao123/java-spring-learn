package design.bridging;

import design.inter.Car;
import design.inter.Engine;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.bridging
 * @descripation TODO
 * @date 2021-07-16
 */
public abstract class RefinedCar implements Car {
    Engine engine;

    public RefinedCar(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void drive() {
        System.out.println("the brand is : " + getBrand());
        engine.start();
    }

    /**
     * TODD
     * @return string
     */
    abstract String getBrand();
}
