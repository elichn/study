package top.elichn.algorithm.array;

/**
 * <p>Title: BinsSearch_704</p>
 * <p>Description: 二分查找 https://leetcode-cn.com/problems/binary-search/</p>
 *
 * @author eli
 * @version 1.0
 * @date 2021/1/1
 */
public class BinsSearch_704 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 8, 9, 12, 13};
        int target = 3;
        System.out.println(search(arr, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 在[left, right]闭区间中查找target
        while (left <= right) {
            // 防止整型溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // target在[mid+1, right]中
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // target在[left, mid-1]中
                right = mid - 1;
            }
        }
        return -1;
    }
}
