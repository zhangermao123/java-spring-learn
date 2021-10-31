package design.chain;

import design.inter.Handler;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.chain
 * @descripation TODO
 * @date 2021-07-21
 */
public class CeoHandler implements Handler {

    @Override
    public Boolean process(Request request) {

        return !request.getName().contains("ceo");
    }
}
