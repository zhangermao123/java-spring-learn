package design.inter;

import design.facade.Company;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.inter
 * @descripation TODO
 * @date 2021-07-21
 */
public interface AdminOfIndustry {
    /**
     * 注册公司
     * @param name String
     * @return Common
     */
    Company register(String name);
}
