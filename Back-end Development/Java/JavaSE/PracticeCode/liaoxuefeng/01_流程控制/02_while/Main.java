// while
public class Main {
    public static void main(String[] args) {
        int sum = 0;
        int m = 20;
        int n = 100;
        // 使用while计算M+...+N:
        while (m <= n) {
            sum += m;
            m++;
        }
        System.out.println(sum);
    }
}