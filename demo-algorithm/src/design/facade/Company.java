package design.facade;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.facade
 * @descripation 外观模式 bean
 * @date 2021-07-21
 */
public class Company {
    //公司名称
    String name;
    //公司id
    String id;
    //公司银行账户
    String bankAccount;
    //公司税号
    String taxCode;

    private Company(String name) {
        this.name = name;
    }

    public static Company builder(String name){
        Company company = new Company(name);
        company.setId(String.valueOf(Math.random()* 10000));
        return company;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", taxCode='" + taxCode + '\'' +
                '}';
    }
}
