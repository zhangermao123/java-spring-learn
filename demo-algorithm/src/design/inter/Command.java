package design.inter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.inter
 * @descripation TODO
 * @date 2021-07-21
 */
public interface Command {

    /**
     * 执行
     */
    void execute();

    /**
     * 回滚
     */
    void undo();
}
