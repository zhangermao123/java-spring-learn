package alogrithm;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation 字符串操作
 * @date 2021-10-13
 */
public class StringTest {
    /**
     * 344. 反转字符串
     * 反转问题
     * 双指针
     * @param s
     * @return char[]
     */
    public char[] reverseString(char[] s) {

        if(s==null || s.length<2){
            return s;
        }
        int right = s.length-1;
        for(int i=0;i<s.length/2;i++){
            char temp = '0';
            temp = s[right];
            s[right] = s[i];
            s[i] = temp;
            right--;
        }
        return s;
    }
}
