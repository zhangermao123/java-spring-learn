package design.facade;

import design.inter.Taxation;

import java.util.UUID;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.facade
 * @descripation TODO
 * @date 2021-07-21
 */
public class OperateTaxCode implements Taxation {

    @Override
    public String createTaxCode(String companyId) {
        return UUID.randomUUID().toString();
    }
}
