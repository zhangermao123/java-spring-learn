package design.template;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.template
 * @descripation TODO
 * @date 2021-07-22
 */
public abstract class AbstractSetTemplate {

    public final String getString(String key){
        String value = lookCache(key);
        if(null == value){
            value = "test";
            putCache(key,value);
            System.out.println(String.format("new load and  key is %s,value is %s",key,value));
        }else{
            System.out.println(String.format("has load and  key is %s,value is %s",key,value));
        }
        return value;
    }

    //子类实现
    protected abstract String lookCache(String key);

    protected abstract void putCache(String key,String value);
}
