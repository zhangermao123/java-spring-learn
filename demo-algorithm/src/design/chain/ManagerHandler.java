package design.chain;

import design.inter.Handler;

import java.math.BigDecimal;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.chain
 * @descripation TODO
 * @date 2021-07-21
 */
public class ManagerHandler implements Handler {

    @Override
    public Boolean process(Request request) {
        if(request.getBigDecimal().compareTo(BigDecimal.valueOf(1000))>0){
            return null;
        }
        return !request.getName().contains("man");
    }
}
