package design.chain;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.chain
 * @descripation
 * @date 2021-07-21
 */
public class Request {
    private String name;
    private BigDecimal bigDecimal;

    public Request(String name, BigDecimal bigDecimal) {
        this.name = name;
        this.bigDecimal = bigDecimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }
}
