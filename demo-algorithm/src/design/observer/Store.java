package design.observer;

import design.inter.ProductObservable;
import design.inter.ProductObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.observer
 * @descripation TODO
 * @date 2021-07-22
 */
public class Store implements ProductObservable {

    private List<ProductObserver> observers = new ArrayList<>();

    private Map<String, Product> products = new HashMap<>();

    @Override
    public void addObserver(ProductObserver observer) {
            this.observers.add(observer);
    }

    @Override
    public void removeObserver(ProductObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * TODD
     * @param product 添加的新产品
     */
    public void addNewProduct(Product product){
        products.put(product.getName(),product);
        observers.forEach(p->new Thread(() -> p.onPublished(product)).start());
    }

    /**
     * 修改产品价格
     * @param product 修改的产品
     */
    public void setProductPrice(Product product){
        Product product1 = products.get(product.getName());
        product1.setPrice(product.getPrice());
        observers.forEach(p->new Thread(() -> p.onPriceChanged(product)).start());
    }
}
