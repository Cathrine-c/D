package 蓝桥;

import java.util.*;

public class Day18 {

        public static void main1(String[] args) {

            new B().getValue();

           // System.out.println(new B().getValue());
            //7

        }


        static class A {

            protected int value;

            public A(int v) {
                setValue(v);
            }


            public void setValue(int value) {
                this.value = value;
            }


            public int getValue() {
                try {
                    value++;
                    return value;

                } catch (Exception e) {
                    System.out.println(e.toString());

                } finally {
                    this.setValue(value);
                    System.out.println(value);
                }
                return value;
            }

        }



        static class B extends A{

            public B(){

                super(5);
                setValue(getValue()-3);

            }


            public void setValue(int value) {
                super.setValue(2*value);
            }

        }


    public static void main2(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxArea(height));

    }

    //暴力解法
     public static int maxArea(int[] height) {

            int s =0;
            int max =0;
            for (int i=0;i<height.length;i++){

                int j=i+1;

                while (j<height.length) {

                    int x = Math.min(height[i],height[j]);

                    s = x*(j-i);
                    max = max>s?max:s;
                    j++;

                }

            }


            return max;

    }


    //指针解法
    /**
     *两个指针，一个指向尾，一个指向头，选取矮的那个向里走，容器底在变小
     * 那么就要关注容器高，所以放弃矮的那个，以寻找更高的
     *
     */
    public static int maxArea1(int[] height) {

        int size = height.length;
        int left = 0;
        int right = size-1;

        int count =0;

        while (left < right) {
            count = Math.max(count,(right-left)*Math.min(height[left],height[right]));

            if (height[left]>height[right]){

                right--;

            }else {

                left++;

            }
        }
        return count;
     }


     //动态规划解法
     public static int maxArea2(int[] height) {

        //边界值判断
         if (height.length <= 1) {
             return -1;
         }

         int i=0,j=height.length-1, s =0;

         while (i < j) {

             int h = Math.min(height[i],height[j]);

             s = Math.max(s,h*(j-i));

             if (height[i]<height[j]){
                 i++;

             }else {
                 j--;

             }
         }

         return s;
     }


    public static void main4(String[] args) {

        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));

    }

    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     */

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length==0){
            return "";
        }

        String s = strs[0];

        for (String string:strs){

            while (!string.startsWith(s)){
                if (s.length() == 0) {
                    return "";
                }

                s = s.substring(0,s.length()-1);

            }

        }

        return s;

    }



    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     */

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();


        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){

            if (nums[i] > 0) break;
            if (i>0&&nums[i]==nums[i-1])continue;

            int left = i+1;int right = nums.length-1;

            while (left < right) {

                int sum = nums[i]+nums[left]+nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));

                    while (left<right&&nums[left]==nums[left+1]) left++;

                    while (left<right&&nums[right]==nums[right-1])right--;

                    left++;
                    right--;

                } else if (sum > 0) {
                    right--;

                }else {
                    left++;

                }
            }
        }

        return result;

    }


    public static void main6(String[] args) {

        int[] nums={-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));

    }


    /**三数之和
     *给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
     * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     */
    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int min = 10000;

        for(int i = 0; i < nums.length-2; i++) {

            if(i > 0 && nums[i] == nums[i-1])
                continue;

            int left = i+1, right = nums.length-1;

            int sum = 0;
            // 双指针
            while(left < right) {
                sum = nums[i]+nums[left]+nums[right];

                if(sum == target)
                    return target;
                if(Math.abs(sum-target) < Math.abs(min-target)) {

                    min = sum;

                }

                if(sum < target) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return min;

    }


    public static void main8(String[] args) {
        int[] nums={0,2,1,-3};
        int tar = 0;
        System.out.println(threeSumClosest(nums, tar));

    }



    public List<String> letterCombinations(String digits) {
       List<String> list = new ArrayList<>();

        if (digits == null || digits.isEmpty()) {
            return list;
        }


        String[] str = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        //键盘上各个键对应的字符串
        String[] arr = new String[digits.length()];

        for (int i=0;i<digits.length();i++){

            char c = digits.charAt(i);

            arr[i] = str[Integer.valueOf(Character.toString(c))-2];

        }

        dfs(arr,0,new StringBuilder(),list);
        return list;

    }


    private void dfs(String[] arr, int index, StringBuilder curStr, List<String> list) {
        if (index == arr.length) {

            list.add(curStr.toString());
            return;
        }

        String str = arr[index];
        int len = str.length();
        for (int i=0;i<len;i++){

            curStr.append(str.charAt(i));

            dfs(arr,index+2,curStr,list);
            curStr.deleteCharAt(index);

        }

    }


    public static int binary(int[] nums,int target){

        int left=0;
        int right = nums.length-1;
        int mid = 0;

        while (left < right) {
            mid = (right+left)/2;

            if (target==nums[mid]){
                return nums[mid];

            }else if (target<nums[mid]){

                right = mid-1;

            }else {
                left = mid+1;

            }

        }

        return -1;


    }


}



