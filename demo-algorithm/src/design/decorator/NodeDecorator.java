package design.decorator;

import design.inter.TextNode;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.decorator
 * @descripation 装饰器
 * @date 2021-07-21
 */
public abstract class NodeDecorator implements TextNode {

    protected final TextNode textNode;

    /**
     * 装饰器constructor 目的是增强的是同一个textNode
     * @param textNode TextNode 生成的TextNode
     */
    protected NodeDecorator(TextNode textNode) {
        this.textNode = textNode;
    }

    @Override
    public void setText(String text) {
        this.textNode.setText(text);
    }
}
