/*
* 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字：
* */
import java.util.*;

public class Exercise1 {
    public static void main(String[] args) {
        final int start = 10;
        final int end = 20;

        List<Integer> list = new ArrayList<>();
        // TODO: 1、根据给定起始范围构造整数序列
        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        // TODO: 2、随机删除List中一个元素
        int removedNumber = list.remove((int) (Math.random() * list.size()));

        int foundNumber = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + foundNumber);
        System.out.println(removedNumber == foundNumber ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        // TODO: 3、找出缺失的数字
        int i = start;
        for (int l: list) {
            if (l > i) {
                return i;
            } else {
                i++;
            }
        }

        return 0;
    }
}