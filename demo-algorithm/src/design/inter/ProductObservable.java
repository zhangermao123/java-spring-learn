package design.inter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.observer
 * @descripation TODO
 * @date 2021-07-22
 */
public interface ProductObservable {

    void addObserver(ProductObserver observer);
    void removeObserver(ProductObserver observer);
}
