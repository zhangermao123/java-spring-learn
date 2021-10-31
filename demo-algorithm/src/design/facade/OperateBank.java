package design.facade;

import design.inter.Bank;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.facade
 * @descripation TODO
 * @date 2021-07-21
 */
public class OperateBank implements Bank {

    @Override
    public String openAccount(String companyId) {
        return String.valueOf(Math.random()* 1000000000);
    }
}
