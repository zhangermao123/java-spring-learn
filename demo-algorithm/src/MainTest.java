import design.inter.Handler;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangwei
 * @version 1.0
 * @className PACKAGE_NAME
 * @descripation TODO
 * @date 2021-06-28
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
         Integer i2 = 200;
//         Integer i3 = 200;
//         int i = 200;
//         long l = 200L;
//         double d = 200d;
//         char s = '孙';
//        File file = new File("/Users/zhangwei/Documents");
//        System.out.println("======================");
//        System.out.println("file path is: "+ file);
//        System.out.println("file Absolut path is: "+ file.getAbsolutePath());
//
//        printFile(file);

        System.out.println("======================");

        getNioFile("/Users/zhangwei/Downloads/logcat/test/test.txt");
//        AlgorithmUtils algorithmUtils = new AlgorithmUtils();
//        int[] nums = {1,1,2};
//        System.out.println("新长度: "+ algorithmUtils.getNewNum(nums));
//        for(int i =0; i< algorithmUtils.getNewNum(nums);i++){
//            System.out.println(nums[i]);
//        }
//        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
//        System.out.println("新长度: "+ algorithmUtils.getNewNum(nums2));
//        for(int n =0; n< algorithmUtils.getNewNum(nums2);n++){
//            System.out.println(nums2[n]);
//        }

    }

    private static void printFile(File file) {
        File[] files = file.listFiles();
        if(null == files || files.length <=0){
            return;
        }
        for(File file1: files){
            System.out.println("child file is : "+ file1);
            printFile(file1);
        }
    }

    private static void getNioFile(String path) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(path,"rw");
        //获取channel
        FileChannel fileChannel = aFile.getChannel();

        //设置buffer
        ByteBuffer buffer = ByteBuffer.allocate(48);

        //按照buffer读取
        while (fileChannel.read(buffer)!=-1){
            //准备读取
            buffer.flip();
            System.out.print(new String(buffer.array()));
            //清除buffer
            buffer.clear();
        }
        //关闭channel
        fileChannel.close();
        //关闭
        aFile.close();
    }

}
