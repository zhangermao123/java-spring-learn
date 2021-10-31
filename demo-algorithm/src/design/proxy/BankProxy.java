package design.proxy;

import design.inter.Bank;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.proxy
 * @descripation 代理模式
 * @date 2021-07-21
 */
public class BankProxy implements Bank {
    private Bank bank;

    public BankProxy(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String openAccount(String companyId) {
        if("1".equals(companyId)){
            throw new RuntimeException("companyId 不能是1");
        }

        return this.bank.openAccount(companyId);
    }
}
