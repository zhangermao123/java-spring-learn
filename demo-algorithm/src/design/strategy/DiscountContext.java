package design.strategy;

import design.inter.DiscountStrategy;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.strategy
 * @descripation TODO
 * @date 2021-07-22
 */
public class DiscountContext {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * 获取最终价格
     * @param total BigDecimal
     * @return java.math.BigDecimal
     */
    public BigDecimal finalCount(BigDecimal total){

        return  total.subtract(this.discountStrategy.getDiscount(total));
    }
}
