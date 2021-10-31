package design.decorator;

import design.inter.TextNode;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.decorator
 * @descripation TODO
 * @date 2021-07-21
 */
public class SpanTextNode implements TextNode {
    private String text;
    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return "<span> " + text + " </span>";
    }
}
