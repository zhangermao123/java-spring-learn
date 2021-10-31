package design.observer;

import design.inter.ProductObserver;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.observer
 * @descripation TODO
 * @date 2021-07-22
 */
public class CustomerObserver implements ProductObserver {


    @Override
    public void onPublished(Product product) {
        System.out.println("CustomerObserver onPublished  "+ product);
    }

    @Override
    public void onPriceChanged(Product product) {
        System.out.println("CustomerObserver onPriceChanged  "+ product);
    }
}
