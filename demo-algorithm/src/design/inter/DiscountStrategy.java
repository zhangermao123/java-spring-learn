package design.inter;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.strategy
 * @descripation TODO
 * @date 2021-07-22
 */
public interface DiscountStrategy {

    /**
     * 计算折扣额度
     * @param total
     * @return
     */
    BigDecimal getDiscount(BigDecimal total);
}
