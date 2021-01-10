package top.elichn.algorithm.num;

/**
 * <p>Title: Reverse——7</p>
 * <p>Description: 整数反转 https://leetcode-cn.com/problems/reverse-integer/</p>
 *
 * @author eli
 * @version 1.0
 * @date 2021/1/9
 */
public class Reverse_7 {

    public static void main(String[] args) {
        // int num = 123;
        // int num = -123;
        int num = 120;
        System.out.println(reverse(num));
    }

    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (result > 214748364 || (result == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (result < -214748364 || (result == -214748364 && tmp < -8)) {
                return 0;
            }
            result = result * 10 + tmp;
            x = x / 10;
        }
        return result;
    }
}
