package design.strategy;

import design.inter.DiscountStrategy;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.strategy
 * @descripation 100满减20
 * @date 2021-07-22
 */
public class OverDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal getDiscount(BigDecimal total) {
        return total.compareTo(new BigDecimal(100))>0?new BigDecimal(20):BigDecimal.ZERO;
    }
}
