package design.strategy;

import design.inter.DiscountStrategy;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.strategy
 * @descripation 普通用户9折
 * @date 2021-07-22
 */
public class UserDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal getDiscount(BigDecimal total) {
        return total.multiply(new BigDecimal("0.1")).setScale(2,BigDecimal.ROUND_DOWN);
    }
}
