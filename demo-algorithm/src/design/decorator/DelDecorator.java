package design.decorator;

import design.inter.TextNode;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.decorator
 * @descripation TODO
 * @date 2021-07-21
 */
public class DelDecorator extends NodeDecorator{

    public DelDecorator(TextNode textNode) {
        super(textNode);
    }

    @Override
    public String getText() {
        return "<del> "+ textNode.getText() + " </del>";
    }
}
