package alogrithm.test0728;

import java.util.Arrays;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm.test0728
 * @descripation TODO
 * @date 2021-07-28
 */
public class ArrayTest {

    public static int getArrayIndex(int[] nums,int target){
        if (null == nums || target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left =0,right = nums.length -1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            if(nums[mid]<target){
                left = mid + 1;
            }else if(nums[mid] == target){
                return mid;
            }else{
                right = mid - 1;
            }
        }
        return  -1;
    }

    public static int getArrayIndex2(int[] nums,int target){
        if(null == nums || nums.length == 0 || nums[0] >= target){
            return 0;
        }

        if(nums[nums.length - 1] == target){
            return nums.length - 1;
        }

        if(nums[nums.length - 1] < target){
            return  nums.length;
        }

        int left = 0, right = nums.length - 1;
        while (left<=right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] == target){
                return mid;
            }else{
                right = mid - 1;
            }
        }
        return right + 1;
    }

    public static int[] getArrayIntArray(int[] nums,int target){
        if(nums == null || nums.length <= 2 || target <=nums[0] || target >= nums[nums.length-1]){
            return new int[]{-1,-1};
        }

        int left = 0, right = nums.length - 1 ;
        while (left<=right){
            int mid = left + ((right - left)>>1);
            if(nums[mid]<target){
                left = mid + 1;
            }else if(nums[mid] == target){
                return new int[]{mid-1,mid+1};
            }else{
                right = mid -1;
            }
        }
        int newleft = left<=right? left:right;
        int newRight = left<=right? right:left;
        return new int[]{newleft,newRight};
    }

    public static int getArraySqrt(int x){
        if(x == 0){
           return 0;
        }
        if(x == 1){
            return 1;
        }
        int left = 0,right = x/2;
        while (left<=right){
            int mid = left + ((right - left )>>1);
            if(mid*mid<x){
              left = mid +1;
            }else if(mid*mid == x){
                return mid;
            }else{
                right = mid - 1;
            }
        }
        return right;
    }

    public static boolean isSqrt(int x){
        if(x <=0){
            return  false;
        }

        if(x == 1 ){
            return  true;
        }

        int left = 0,right = x/2;
        while (left<=right){
            int mid = left + ((right - left )>>1);
            if(mid*mid<x){
                left = mid + 1;
            }else if(mid*mid == x){
                return true;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }



    static class MyLinkedList{
        private ListNode head;
        private int size;
        class ListNode{
             int val;
             ListNode next;
            public ListNode(int val) {
                this.val = val;
            }
        }

        public MyLinkedList() {
            head = new ListNode(0);
            size = 0;
        }

        public int get(int index){
            if(index<0 || index>= size){
                return -1;
            }
            ListNode cur = head;
            for(int i=0;i<=index;i++){
                cur = cur.next;
            }
            return  cur.val;
        }

        public void addAtIndex(int index, int val){
            if(index<0){
                index = 0;
            }
            if(index>size){
               return;
            }
            ListNode cur = head;
            for(int i=0; i<index;i++){
                cur = cur.next;
            }
            ListNode addNode = new ListNode(val);
            addNode.next = cur.next;
            cur.next = addNode;
            size ++;
        }

        public void addAtHead(int val){
            addAtIndex(0,val);
        }

        public void addAtTail(int val){
            addAtIndex(size,val);
        }

        public void deleteAtIndex(int index){
            if(index<0 || index >=size){
               return;
            }
            ListNode cur = head;
            for(int i=0;i<=index;i++){
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3


    }
}
