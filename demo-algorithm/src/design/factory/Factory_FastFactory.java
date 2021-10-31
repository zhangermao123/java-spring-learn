package design.factory;

import design.inter.AbstractFactory;
import design.inter.HtmlFactory;
import design.inter.WordFactory;

/**
 * @author zhangwei
 * @version 1.0
 * @className design
 * @descripation TODO
 * @date 2021-07-16
 */
public class Factory_FastFactory implements AbstractFactory {

    @Override
    public HtmlFactory createHtml() {
        return new Factory_FastHtml();
    }

    @Override
    public WordFactory createWord() {
        return new Factory_FastWord();
    }
}
