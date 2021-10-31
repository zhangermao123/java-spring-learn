package design.inter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.inter
 * @descripation TODO
 * @date 2021-07-21
 */
public interface Taxation {

    /**
     * 生成税号
     * @param companyId string 公司id
     * @return 税号
     */
    String createTaxCode(String companyId);
}
