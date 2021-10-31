package alogrithm;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation 时间/空间复杂度
 * @date 2021-07-22
 */
public class RecursionTest {
    /**
     * 时间复杂度 O(n)
     * @param x
     * @param n
     * @return long
     */
    public static long function1(int x,int n){

        long result = 1;
        for(int i = 0;i<n;i++){
            result = result *x;
        }

        return result;
    }

    /**
     * 时间复杂度 O(n)
     * @param x
     * @param n
     * @return long
     */
    public static long function2(int x, int n){
        if(n==0){
            return 1;
        }
        return function2(x,n-1)*x;
    }

    public static long function3(int x,int n){
        if(n ==0){
            return 1;
        }
        if(n%2 ==1){
            return function3(x,n/2)*function3(x,n/2)*x;
        }

        return function3(x,n/2)*function3(x,n/2);
    }

    /**
     * 时间复杂度f(n) = O(2^n)  每次递归时间复杂度[加法]0(1)*递归次数[二叉树](2^n-1) = 2^n
     * 递归算法的空间复杂度 = 每次递归的空间复杂度 * 递归深度  S(n) = O(n) O(1)*n = O(n)
     * 测试 如果 i = 50 耗时：61194
     * @param i 相加次数
     * @return
     */
    public static long fibonacci1(int i) {
        if(i <= 0) {
            return 0;
        }
        if(i == 1) {
            return 1;
        }
        return fibonacci1(i-1) + fibonacci1(i-2);
    }


    public static long fibonacci2(long first,long second,int n){
        if (n <= 0) {
            return 0;
        }else if (n < 3) {
            return 1;
        } else if (n == 3) {
            return first + second;
        } else {
            return fibonacci2(second, first + second, n - 1);
        }
    }

}
