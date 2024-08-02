package leetcode;

import java.util.Arrays;
import java.util.Optional;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,2,3,4};
        System.out.println(maxOperations(nums));
    }

    public static int maxOperations(int[] nums) {
        int res = 1;
        compute(nums, res, null);

        return res;
    }

    public static void compute(int[] nums, int res, Integer flag) {
        if (nums.length == 0 || nums.length == 1 || nums.length == 2) {
            return;
        }
        int start = 0;
        int end = nums.length - 1;

        int a = nums[start] + nums[start+1];
        int b = nums[start] + nums[end];
        int c = nums[end-1] + nums[end];

        if (a != b && a != c && b!= c) {
            return;
        }

        if (flag == null) {
            if (a == b) {
                nums = Arrays.copyOfRange(nums, start+1, end);
                flag = Optional.ofNullable(flag).orElse(a);
                res++;
                compute(nums, res, flag);
            }
            if (a == c) {
                nums = Arrays.copyOfRange(nums, start+1, end-1);
                flag = Optional.ofNullable(flag).orElse(a);
                res++;
                compute(nums, res, flag);
            }
            if (b == c) {
                nums = Arrays.copyOfRange(nums, start, end-1);
                flag = Optional.ofNullable(flag).orElse(b);
                res++;
                compute(nums, res, flag);
            }
        } else {
            if (a == flag) {
                nums = Arrays.copyOfRange(nums, start+1, end);
                res++;
                compute(nums, res, flag);
            }
            if (b == flag) {
                nums = Arrays.copyOfRange(nums, start+1, end-1);
                res++;
                compute(nums, res, flag);
            }
            if (c == flag) {
                nums = Arrays.copyOfRange(nums, start, end-1);
                res++;
                compute(nums, res, flag);
            }
        }
    }

}
