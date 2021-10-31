package design.facade;

import design.inter.AdminOfIndustry;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.facade
 * @descripation TODO
 * @date 2021-07-21
 */
public class OpenIndustry implements AdminOfIndustry {


    @Override
    public Company register(String name) {
        return Company.builder(name);
    }
}
