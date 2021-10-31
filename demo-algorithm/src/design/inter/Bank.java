package design.inter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.inter
 * @descripation TODO
 * @date 2021-07-21
 */
public interface Bank {

    /**
     * 银行开户
     * @param companyId string 公司id
     * @return 账户id
     */
    String openAccount(String companyId);
}
