package design.node;

import design.inter.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.node
 * @descripation TODO
 * @date 2021-07-21
 */
public class CommentNode implements Node {
    private String text;

    public CommentNode(String text) {
        this.text = text;
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
        return "<!-- "+text+" -->";
    }
}
