import alogrithm.ArrayTest;
import alogrithm.RecursionTest;

/**
 * @author zhangwei 
 * @version 1.0
 * @className PACKAGE_NAME
 * @descripation
 * @date 2021-06-28
 */
public class AlgorithmUtils {

    public int getNewNum(int[] num){

        if(num == null || num.length <=0){
            return 0;
        }
        int count = 0;//表示第几个指针
        for(int i=1;i<num.length;i++){
            if(num[count] != num[i]){
                num[++count] = num[i];//先添加在使用
            }
        }
        return count+1;

    }

    public static void main(String[] args) {
//        testTime(2000000000L);
        long start = System.currentTimeMillis();
//        System.out.println("开始");
////        long base = RecursionTest.fibonacci1(10);//55
//        long base = RecursionTest.fibonacci2(1,1,50);//
//        System.out.println(base);
        boolean target = ArrayTest.midArray5(1);
        System.out.println(target);
        long end = System.currentTimeMillis();
        System.out.println("结束，耗时："+ (end-start));

    }


    /**
     * 本机时间复杂度O(n) 进行2*10^8 大概用时1s
     * @param
     */
    public static void testTime(long dl){

        long start = System.currentTimeMillis();
        System.out.println("开始");
        long k = 0;
        for(long i = 0; i< dl;i++){
            k++;
        }
        long end = System.currentTimeMillis();
        System.out.println("结束，耗时："+ (end-start));
    }



}
