package design.node;

import design.inter.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.node
 * @descripation TODO
 * @date 2021-07-16
 */
public class TextNode implements Node {
    private String name;

    public TextNode(String name) {
        this.name = name;
    }

    @Override
    public Node add(Node node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Node> child() {
        return new ArrayList<>();
    }

    @Override
    public String toXml() {
        return name;
    }
}
