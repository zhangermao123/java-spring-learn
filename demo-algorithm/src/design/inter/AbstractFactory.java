package design.inter;

import design.factory.Factory_FastFactory;
import design.factory.Factory_WordFactory;

/**
 * @author zhangwei
 * @version 1.0
 * @className design
 * @descripation 抽象工厂
 * @date 2021-07-16
 */
public interface AbstractFactory {
    /**
     * TODD
     * @return
     */
    HtmlFactory createHtml();

    /**
     * TODD
     * @return
     */
    WordFactory createWord();

    /**
     * TODD 抽象工厂 多个工厂对应多个产品
     * @param name
     * @return design.inter.AbstractFactory
     */
    public static AbstractFactory createFactory(String name){
        switch (name.toLowerCase()){
            case "fast":
                return new Factory_FastFactory();
            case "good":
                return new Factory_WordFactory();
            default:
                throw new IllegalArgumentException("Invalid factory name");
        }
    }
}
