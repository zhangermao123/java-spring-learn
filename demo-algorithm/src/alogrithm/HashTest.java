package alogrithm;

import java.util.*;

/**
 * @author zhangwei
 * @version 1.0
 * @className alogrithm
 * @descripation 哈希法
 * 一般来说哈希表都是用来快速判断一个元素是否出现集合里。（也用来判断环链表等重复循环）
 * 1.一般集合大小固定用数组
 * 2.唯一判定用set
 * 3.key-value 用map
 * @date 2021-10-12
 */
public class HashTest {
    /**
     * 242.有效的字母异位词
     *  哈希法（数组有极限值）
     * @param
     * @param s
     * @param t
     * @return boolean
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charAry = new int[26];
        for(char sc: s.toCharArray()){
            charAry[sc-'a'] += 1;
        }

        for(char tc: t.toCharArray()){
            charAry[tc-'a'] -= 1;
        }

        for(int n: charAry){
            if(n!=0){
                return false;
            }
        }
        char temp = '0';
        return true;
    }
    /**
     * 349. 两个数组的交集
     * 哈希法（不知道极限值，适用set）
     * @param nums1
     * @param nums2
     * @return int[]
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length<=0 || nums2 == null ||nums2.length<=0){
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> reSet = new HashSet<>();

        for(int n: nums1){
            set1.add(n);
        }

        for(int n: nums2){
            if(set1.contains(n)){
                reSet.add(n);
            }
        }
        int[] result = new int[reSet.size()];
        int index = 0;

        for(int n: reSet){
            result[index++] = n;
        }

        return result;
    }

    /**
     * 202. 快乐数
     * 哈希 不快乐数会循环
     * @param n
     * @return boolean
     */
    public boolean isHappy(int n) {

        Set<Integer> rset = new HashSet<>();
        while(n!=1){
            n = getNums(n);
            if(rset.contains(n)){
                return false;
            }
            rset.add(n);
        }
        return true;
    }

    private int getNums(int n){
        int nums = 0 ;
        while(n>=1){
            int temp = n%10;
            nums +=temp*temp;
            n = n/10;
        }
        return nums;
    }


    /**
     * 1. 两数之和
     * hashmap 查询时间复杂度O(1) 比遍历快
     * 时间复杂度 是O(n) * O(1)
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];
        if(nums ==null || nums.length ==0){
            return res;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(Integer key: map.keySet()){

        }
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                res[1] = i;
                res[0] = map.get(temp);
                break;
            }
            map.put(nums[i],i);
        }
        return res;
    }
    /**
     * 454. 四数相加 II
     * 和两数之和类似 先哈希一个map，然后和另外两组比较， 时间复杂度上拆半最小
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return int
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        if(nums1 == null || nums1.length<=0){
            return count;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int n: nums1){
            for(int m: nums2){
                map.put(n+m,map.getOrDefault(n+m,0)+1);
            }
        }

        for(int n: nums3){
            for(int m: nums4){
                int temp = 0-n-m;
                if(map.containsKey(temp)){
                    count += map.get(temp);
                }
            }
        }
        return count;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return boolean
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        if(magazine.length()<ransomNote.length()){
            return  false;
        }
        int[] charNums = new int[26];
        for(char ca: magazine.toCharArray()){
            charNums[ca -'a'] += 1;
        }

        for(char ca: ransomNote.toCharArray()){
            if(charNums[ca-'a']==0){
                return false;
            }
            charNums[ca-'a'] -= 1;
        }
        return true;
    }

    /**
     * 15. 三数之和
     * 由于要考虑去重（用哈希需要考虑hash去重），而且对数组下标无要求,所以用循环双指针完成
     * @param nums
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length<3){
            return result;
        }

        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            //排序后，如果第一个元素>0,后续不需要考虑了
            if(nums[i]>0){
                return result;
            }
            //去重逻辑
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int right = nums.length-1;
            int left = i+1;
            while(left<right){
                int sum = nums[i]+nums[left]+nums[right];
                if( sum > 0){
                    right--;
                }else if(sum == 0){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    // 去重逻辑应该放在找到一个三元组之后 防止数组nums后续有重复数据
                     while (right > left && nums[right] == nums[right - 1]) {
                         right--;
                     }
                     while (right > left && nums[left] == nums[left + 1]) {
                         left++;
                     }
                    right--;
                    left++;
                }else{
                    left++;
                }
            }
        }

        return result;
    }

    /**
     * 18. 四数之和
     * 是三数之和的扩展。在三数之和基础上嵌套增加for循环
     * @param nums
     * @param target
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || nums.length< 4){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            if(nums[i]>target){
                return result;
            }
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){

                if(j > i+1 && nums[j] == nums[j - 1]){
                    continue;
                }
                int right = nums.length-1;
                int left = j+1;
                while(left<right){
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if( sum > target){
                        right--;
                    }else if(sum == target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        // 去重逻辑应该放在找到一个三元组之后 防止数组nums后续有重复数据
                        while (right > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while (right > left && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        right--;
                        left++;
                    }else{
                        left++;
                    }
                }
            }

        }
        return result;
    }
}
