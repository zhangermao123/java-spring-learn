package design.inter;

import design.observer.Product;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.observer
 * @descripation TODO
 * @date 2021-07-22
 */
public interface ProductObserver {

    /**
     * 发布产品
     * @param product Product
     */
    void onPublished(Product product);

    /**
     * 修改价格
     * @param product Product
     */
    void onPriceChanged(Product product);
}
