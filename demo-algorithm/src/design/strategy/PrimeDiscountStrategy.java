package design.strategy;

import design.inter.DiscountStrategy;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.strategy
 * @descripation 允许在满100减20的基础上对Prime会员再打七折
 * @date 2021-07-22
 */
public class PrimeDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal getDiscount(BigDecimal total) {
        BigDecimal overDisCount= new OverDiscountStrategy().getDiscount(total);
        return overDisCount.add(total.subtract(overDisCount).multiply(new BigDecimal("0.3")).setScale(2,BigDecimal.ROUND_DOWN));
    }
}
