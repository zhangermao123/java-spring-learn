package design.facade;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.facade
 * @descripation TODO
 * @date 2021-07-21
 */
public class Facade {
    private OpenIndustry openIndustry;
    private OperateBank operateBank;
    private OperateTaxCode operateTaxCode;

    public Facade() {
        openIndustry = new OpenIndustry();
        operateBank = new OperateBank();
        operateTaxCode = new OperateTaxCode();
    }

    public  Company openCompany(String name){
       Company company = this.openIndustry.register(name);
       company.setBankAccount(this.operateBank.openAccount(company.getId()));
       company.setTaxCode(this.operateTaxCode.createTaxCode(company.getId()));
       return company;
    }
}
