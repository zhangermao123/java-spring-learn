package design.inter;

import java.util.List;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.inter
 * @descripation TODO
 * @date 2021-07-16
 */
public interface Node {
    /**
     * TODD 添加节点
     * @param node Node
     * @return Node
     */
    Node add(Node node);

    /**
     * TODD 获取子节点
     * @return  List
     */
    List<Node> child();

    /**
     * TODD 获取最终xml
     * @return
     */
    String toXml();
}
