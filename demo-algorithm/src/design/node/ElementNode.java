package design.node;

import design.inter.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.node
 * @descripation 支节点
 * @date 2021-07-16
 */
public class ElementNode implements Node {
    private String name;
    private List<Node> list = new ArrayList<>();

    public ElementNode(String name) {
        this.name = name;
    }

    @Override
    public Node add(Node node) {
        list.add(node);
        return this;
    }

    @Override
    public List<Node> child() {
        return list;
    }

    @Override
    public String toXml() {
        //一个name对应两个节点
        String start = "<"+ name+">\n";
        String end = "</"+ name+">\n";
        StringJoiner sj = new StringJoiner("",start,end);
        list.forEach(node -> {
            sj.add(node.toXml()+"\n");

        });
        return sj.toString();
    }
}
