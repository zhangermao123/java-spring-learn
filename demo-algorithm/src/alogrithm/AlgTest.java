package alogrithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation TODO
 * @date 2021-07-28
 */
public class AlgTest {

    public static void main(String[] args) {
//        System.out.println("======================");
//        HashTest test = new HashTest();
//        boolean isHasp = test.isHappy(19);
//        MultiThreadTest test = new MultiThreadTest(26);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test.printAbyLock();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test.printBbyLock();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test.printCbyLock();
//            }
//        }).start();

//
//
//        int[] changeArray = {7, 1, 3, 5, 13, 9, 3, 6, 11};
//        ArrayTest.quickSort(changeArray);
//        System.out.println("last : "+Arrays.toString(changeArray));
//        System.out.println("======================");
        System.out.println("请输入两个值");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            System.out.println("...."+sc.nextInt());
        }
    }

}
