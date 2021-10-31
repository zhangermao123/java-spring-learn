package alogrithm;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation 输出A1B2C3....B25C26
 * @date 2021-10-15
 */
public class MultiThreadTest {

    private int time;
    private int state = 0;
    private final Object statA = new Object();
    private final Object statB = new Object();
    private final Object statC = new Object();


    public MultiThreadTest(int time) {
        this.time = time;
    }

    /**
     * wait 和notify 实现多线程（对象无法精确的通知那个线程）
     *
     * @param
     * @param name
     * @param currState
     * @param curr
     * @param next
     * @return void
     */
    private void print(String name, int currState, Object curr, Object next){
        int count = time%3 == 0 ? time/3: time/3 +1;

       for(int i=0; i<count;){
            synchronized(curr){
                while(state%3!=currState){
                    try {
                        curr.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(state%3==currState && state<time){
//                    System.out.println("now i is: "+ i);
//                    System.out.println("now state is: "+ state);
                    System.out.print(name+(state+1));
                }
                i++;
                state++;
                synchronized (next){
                    next.notify();
                }

            }
       }
    }

    public void printA(){
        print("A",0,statA,statB);
    }

    public void printB(){
        print("B",1,statB,statC);
    }

    public void printC(){
        print("C",2,statC,statA);
    }


    private ReentrantLock lock = new ReentrantLock();
    //lock条件
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private void printByLock(String name, int currState, Condition curr, Condition next){
        int count = time%3 == 0 ? time/3: (time/3 +1);
        for(int i=0;i<count;){
            lock.lock();
            try {

                while(state%3!=currState) {
                    curr.await();
                }
                if(state%3==currState && state<time){
                    System.out.println(name+(state+1));
                }
                i++;
                state++;
                next.signalAll();
            }catch (InterruptedException e){

            }finally {
                lock.unlock();
            }

       }
    }

    public void printAbyLock(){
        printByLock("A",0,conditionA,conditionB);
    }

    public void printBbyLock(){
        printByLock("B",1,conditionB,conditionC);
    }

    public void printCbyLock(){
        printByLock("C",2,conditionC,conditionA);
    }
}
