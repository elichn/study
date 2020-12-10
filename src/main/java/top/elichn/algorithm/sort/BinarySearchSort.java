package top.elichn.algorithm.sort;

/**
 * <p>Title: BinarySearchSort</p>
 * <p>Description: BinarySearchSort</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/3/22
 */
public class BinarySearchSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 8, 9, 12, 13};
        int target = 13;
        System.out.println(binarySearch(arr, target));
    }

    public static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        // 在[l, r]闭区间中查找target
        while (l <= r) {
            // 防止整型溢出
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            }
            // target在[mid+1, r]中
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                // target在[mid+1, r]中
                r = mid - 1;
            }
        }
        return -1;


    }
}
