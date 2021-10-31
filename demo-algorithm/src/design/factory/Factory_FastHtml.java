package design.factory;

import design.inter.HtmlFactory;

/**
 * @author zhangwei
 * @version 1.0
 * @className design
 * @descripation TODO
 * @date 2021-07-16
 */
public class Factory_FastHtml implements HtmlFactory {

    @Override
    public String save(String html) {
        return "this is fast html : "+ html;
    }
}
