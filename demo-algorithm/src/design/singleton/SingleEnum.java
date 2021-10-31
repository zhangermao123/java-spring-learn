package design.singleton;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.singleton
 * @descripation TODO
 * @date 2021-07-16
 */
public enum SingleEnum {

    INSTANCE;

    private String name = "test";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
