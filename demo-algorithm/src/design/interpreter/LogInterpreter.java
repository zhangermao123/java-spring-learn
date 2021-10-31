package design.interpreter;

/**
 * @author zhangwei
 * @version 1.0
 * @className design.interpreter
 * @descripation TODO
 * @date 2021-07-22
 */
public class LogInterpreter {

    public static String log(String oldStr, Object... objs) {
        if (null == oldStr) {
            return "";
        }
        if (!oldStr.contains("{}") && objs.length == 0) {
            return oldStr;
        }

        int i = 0;
        StringBuilder sb = new StringBuilder(oldStr);
        for (Object object : objs) {
            i = sb.toString().indexOf("{}", i);
            sb.replace(i, i+2, String.valueOf(object));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

}
