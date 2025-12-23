/*
* 其它条件与Exercise1一致但序列不连续，试找出缺失的数字：
* */
import java.util.*;

public class Exercise2 {
    public static void main(String[] args) {
        final int start = 10;
        final int end = 20;

        List<Integer> list = new ArrayList<>();
        // TODO: 1、根据给定起始范围构造整数序列
        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        // 洗牌算法shuffle可以随机交换List中的元素位置:
        Collections.shuffle(list);

        // TODO: 2、随机删除List中一个元素
        int removedNumber = list.remove((int) (Math.random() * list.size()));

        int foundNumber = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + foundNumber);
        System.out.println(removedNumber == foundNumber ? "测试成功" : "测试失败");
    }

    static int findMissingNumber(int start, int end, List<Integer> list) {
        // TODO: 3、找出缺失的数字
        int result = 0;

        for (int l: list) {
            result += l;
        }

        result = ((start + end) * (end - start + 1)) / 2 - result;

        return result;
    }
}