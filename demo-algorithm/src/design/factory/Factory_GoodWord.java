package design.factory;

import design.inter.WordFactory;

/**
 * @author zhangwei
 * @version 1.0
 * @className design
 * @descripation TODO
 * @date 2021-07-16
 */
public class Factory_GoodWord implements WordFactory {

    @Override
    public String save(String word) {
        return "this is good word : "+ word;
    }

}
