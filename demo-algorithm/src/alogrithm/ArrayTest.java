package alogrithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation 数组  二分法/双指针/滑块
 * @date 2021-07-23
 */
public class ArrayTest {

//---------------------------------------------------------二分法

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
     * <p>
     * 二分法查找，两种原则,[] 左闭右闭， [) 左闭右开
     *
     * @param nums   int[]
     * @param target 目标值
     * @return 下标
     */
    public static int midArray(int[] nums, int target) {
        if (null == nums || target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //除2用位运算更快
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                //左闭右包
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                //右包
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 二分法 [] 左闭右闭
     * 总共4种情况
     *
     * @param nums   数组
     * @param target 目标值
     * @return int
     */
    public static int midArray2(int[] nums, int target) {

        if (null == nums || nums.length <= 0 || target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置
     * 数组中不存在目标值，返回 [-1, -1]
     * []
     *
     * @param nums
     * @param target
     * @return int[]
     */
    public static int[] midArray3(int[] nums, int target) {
        if (null == nums || nums.length <= 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return new int[]{mid - 1, mid + 1};
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * x 的平方根
     * []
     *
     * @param x int
     * @return int
     */
    public static int midArray4(int x) {
        int fx = x;
        if (x < 0) {
            fx = -x;
        }
        if (fx == 0) {
            return 0;
        } else if (fx == 1) {
            return 1;
        }
        int left = 0, right = fx >> 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid * mid < fx) {
                left = mid + 1;
            } else if (mid * mid == fx) {
                if (x < 0) {
                    return -mid;
                }
                return mid;
            } else {
                right = mid - 1;
            }
        }
        if (x < 0) {
            return -right;
        }
        return right;
    }

    /**
     * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
     * 说明：不要使用任何内置的库函数，如  sqrt。
     * 完全平方数 如1，4，9，16
     *
     * @param x
     * @return int
     */
    public static boolean midArray5(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0 || x == 1) {
            return true;
        }
        int left = 0, right = x >> 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (mid * mid < x) {
                left = mid + 1;
            } else if (mid * mid == x) {
                return true;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

//------------------------------------------------

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * @param nums
     * @param val
     * @return int
     */
    public static int removeArray(int[] nums, int val) {
        int showIndex = 0;
        for (int firstIndex = 0; firstIndex < nums.length; firstIndex++) {
            if (nums[firstIndex] != val) {
                nums[showIndex++] = nums[firstIndex];
            }
        }
        return showIndex;
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度
     *
     * @param nums
     * @return int
     */
    public static int removeRepeat(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        int showIndex = 0;
        for (int firstIndex = 1; firstIndex < nums.length; firstIndex++) {
            if (nums[showIndex] != nums[firstIndex]) {
                nums[++showIndex] = nums[firstIndex];
            }
        }
        return showIndex + 1;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度
     *
     * @param nums
     * @return int
     */
    public static int[] removeRepeatTwo(int[] nums) {
        if (nums.length <= 2) {
            return nums;
        }
        int showIndex = 2;
        for (int firstIndex = 2; firstIndex < nums.length; firstIndex++) {
            if (nums[showIndex - 2] != nums[firstIndex]) {
                nums[showIndex++] = nums[firstIndex];
            }
        }

        return Arrays.copyOf(nums, showIndex);
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * @param nums
     * @return int
     */
    public static int[] moveZero(int[] nums) {
        if (nums.length <= 0) {
            return nums;
        }
        int showIndex = 0;
        for (int firstIndex = 0; firstIndex < nums.length; firstIndex++) {
            if (nums[firstIndex] != 0) {
                nums[showIndex++] = nums[firstIndex];
            }
        }
        while (showIndex < nums.length) {
            nums[showIndex++] = 0;
        }
        return nums;
    }

    /**
     * 给定 S 和 T 两个含有退格字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * @param s
     * @param t
     * @return boolean
     */
    public static boolean judgeAequalB(String s, String t) {
        int sSIndex = 0, tSIndex = 0;
        char[] sChar = s.toCharArray(), tChar = t.toCharArray();
        for (int sFIndex = 0; sFIndex < sChar.length; sFIndex++) {
            if (sChar[sFIndex] != '#') {
                sChar[sSIndex] = sChar[sFIndex];
                sSIndex++;
            } else {
                sSIndex = (sSIndex > 1) ? sSIndex - 2 : 0;
            }

        }
        String newS = Arrays.toString(Arrays.copyOf(sChar, sSIndex));

        for (int tFIndex = 0; tFIndex < tChar.length; tFIndex++) {
            if (tChar[tFIndex] != '#') {
                tChar[tSIndex] = tChar[tFIndex];
                tSIndex++;
            } else {
                tSIndex = (tSIndex > 1) ? tSIndex - 2 : 0;
            }

        }
        String newT = Arrays.toString(Arrays.copyOf(tChar, tSIndex));

        if ((null == newS && null == newT) || (newS.equals(newT))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 有序数组的平方
     * 思路是 最大值肯定在左右两端，中间最小
     * 双指针-从大到小倒序
     * @param
     * @return int[]
     */
    public static int[] getSquare(int[] A) {
        int start = 0, end = A.length - 1, showIndex = A.length - 1;
        int[] nums = new int[A.length];
        while (showIndex >= 0) {
            nums[showIndex--] = A[start] * A[start] >= A[end] * A[end] ? A[start] * A[start++] : A[end] * A[end--];
        }
        return nums;
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     *
     * @param
     * @param val
     * @param nums
     * @return int
     */
    public static int getLengthByArray(int val, int[] nums) {
        int num = 0;
        int showIndex = 0;
        int lastLength = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            num += nums[j];
            while (num >= val) {
                lastLength = Math.min(j - showIndex + 1, lastLength);
                num -= nums[showIndex++];
            }
        }

        return lastLength == Integer.MAX_VALUE ? 0 : lastLength;
    }

    /**
     * 水果成篮，主要查找相邻最长两个数组，使用的的是滑块思想，本质还是双指针
     *
     * @param
     * @param tree
     * @return int
     */
    public static int getLengthByTree(int[] tree) {
        if (tree == null || tree.length <= 0) {
            return 0;
        }
        //最后长度
        int maxLength = 0;
        //初始指针位置
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();

        //循环遍历数组
        for (int i = 0; i < tree.length; i++) {
            map.put(tree[i], map.getOrDefault(tree[i], 0) + 1);
            //当有第三个元素的时候，把双元素滑块，左边的的值remove，并且将长度置同maxLength比较
            while (map.size() > 2) {
                map.put(tree[left], map.get(tree[left]) - 1);
                if (map.get(tree[left]) == 0) {
                    map.remove(tree[left]);
                }
                left++;
            }
            //计算滑块终指针-初指针
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    /**
     * 59.螺旋矩阵II
     * 跑马灯，二分法逻辑， 左闭右开
     * @param val int
     * @return
     */
    public static int[][] generateMatrix(int val){

        // 定义二元数组
        int[][] result = new int[val][val];
        // 定义赚的圈数
        int loop = val/2;
        // 定义起始位置 x 0
        int startX = 0;
        // 定义起始位置y 0;
        int startY = 0;
        // 定义偏移量
        int offset = 1;
        // 坐标对应值
        int value = 1;
        while(loop >0 ){
            int i = startX;
            int j = startY;

            // 上层从左到右（左闭右开）
            for(;j<startY+val-offset;j++){
                result[startX][j] = value++;
            }

            // 右侧从上往下(左闭右开) 这个时候j在最右侧
            for(;i<startX+val-offset;i++){
                result[i][j] = value++;
            }

            // 下层从右到左(左闭右开)，这时候i,j在最右
            for(;j>startY;j--){
                result[i][j] = value++;
            }

            // 左侧从下到上(左闭右开)，这时候i在最下，j在最左
            for(;i>startX;i--){
                result[i][j] = value++;
            }
            //一次循环结束
            loop--;
            //最左坐标变大1
            startX +=1;
            startY +=1;
            //偏移量+2；
            offset +=2;
        }

        //考虑奇数情况
        if(val%2 == 1){
           result[val/2][val/2] = value;
        }

        return result;
    }


    /**
     * 快速排序
     * @param array
     */
    public static void quickSort(int[] array) {
        int len;
        if(array == null
                || (len = array.length) == 0
                || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
    }

    /**
     * 快排核心算法，递归实现
     * @param array
     * @param left
     * @param right
     */
    public static void sort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }
}
