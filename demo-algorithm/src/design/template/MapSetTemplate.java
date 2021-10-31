package design.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.template
 * @descripation TODO
 * @date 2021-07-22
 */
public class MapSetTemplate extends AbstractSetTemplate{

    private Map<String,String> map = new HashMap<>();

    public MapSetTemplate() {
    }

    @Override
    protected String lookCache(String key) {
        return map.get(key);
    }

    @Override
    protected void putCache(String key, String value) {
        map.put(key,value);
    }
}
