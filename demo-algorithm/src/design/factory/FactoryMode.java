package design.factory;

import com.sun.xml.internal.rngom.binary.DataPattern;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zhangwei
 * @version 1.0
 * @className design
 * @descripation 工厂模式
 * @date 2021-07-16
 */
public class FactoryMode {

    //静态工厂方法实现一个类似20200202的整数转换为LocalDate
    public static LocalDate fromInt(int yyyyMMdd) {
        if(yyyyMMdd<=10000000) {
            throw new RuntimeException("时间参数错误");
        }
        return LocalDate.of(yyyyMMdd/10000,(yyyyMMdd/100)%100,yyyyMMdd%100);
    }
}
