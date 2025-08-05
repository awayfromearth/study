// 圆周率π可以使用公式计算：Π / 4 = 1 - 1 / 3 + 1 / 5 - 1/ 7 + 1 / 9 - ...
public class PI {
    public static void main(String[] args) {
        double pi = 0;
        for (double i = 1.0; i <= 999999999; i++) {
            pi += i % 2 == 0 ? (-4 / (2 * i - 1)) : (4 / (2 * i - 1));
        }
        System.out.println(pi);
    }
}
