package design.inter;

import design.chain.Request;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.inter
 * @descripation 责任链
 * @date 2021-07-21
 */
public interface Handler {

    /**
     * 返回Boolean.TRUE = 成功
     * 返回Boolean.FALSE = 拒绝
     * 返回null = 交下一个处理
     * @param request Request
     * @return
     */
    Boolean process(Request request);
}
