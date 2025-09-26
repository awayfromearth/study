// 计算前N个自然数的和
public class Main {
    public static void main(String[] args) {
        int n = 100;
        // TODO: sum = 1 + 2 + ... + n
        int sum = (1 + n) * n / 2;
        System.out.println(sum);
        System.out.println(sum == 5050 ? "测试通过" : "测试失败");
    }
}
