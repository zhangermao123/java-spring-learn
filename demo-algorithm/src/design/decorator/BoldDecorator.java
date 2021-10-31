package design.decorator;

import design.inter.Node;
import design.inter.TextNode;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.decorator
 * @descripation TODO
 * @date 2021-07-21
 */
public class BoldDecorator extends NodeDecorator {

    public BoldDecorator(TextNode textNode) {
        super(textNode);
    }


    @Override
    public String getText() {
        return "<b> "+ textNode.getText()+ " </b>";
    }
}
