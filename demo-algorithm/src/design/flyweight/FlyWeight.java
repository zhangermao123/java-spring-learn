package design.flyweight;

import design.copy.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.flyweight
 * @descripation TODO
 * @date 2021-07-21
 */
public class FlyWeight {
    private String id;
    private String name;
    private static final Map<String, FlyWeight> cache = new HashMap<>();

    private FlyWeight(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FlyWeight create(String id, String name) {
        String key = id + "\\" + name;
        FlyWeight flyWeight = cache.get(key);

        if (null == flyWeight) {
            flyWeight = new FlyWeight(id, name);
            System.out.println("FlayWight create new FlayWight(" + id + "," + name + ")");
            cache.put(key, flyWeight);
        } else {
            System.out.println("FlayWight cache has this FlayWight(" + id + "," + name + ")");
        }
        return flyWeight;
    }
}
