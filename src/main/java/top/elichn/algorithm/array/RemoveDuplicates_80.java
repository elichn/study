package top.elichn.algorithm.array;

/**
 * <p>Title: RemoveDuplicates_80</p>
 * <p>Description: 删除排序数组中的重复项 II https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class RemoveDuplicates_80 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int result = removeDuplicates(nums);
        System.out.println(result);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static int removeDuplicates(int[] nums) {
        int k = 0;
        if (nums.length <= 2) {
            return nums.length;
        }
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k]) {
                k++;
                nums[k] = nums[i];
                count = 1;
            } else {
                if (count == 1) {
                    k++;
                    nums[k] = nums[i];
                    count++;
                }
            }
        }
        return k + 1;
    }
}
