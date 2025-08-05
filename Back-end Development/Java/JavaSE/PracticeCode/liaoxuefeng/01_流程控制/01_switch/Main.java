import java.util.Scanner;

/**
 * switch实现石头/剪子/布并判断胜负
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("please choice:");
		System.out.println(" 1: Rock");
		System.out.println(" 2: Scissors");
		System.out.println(" 3: Paper");
		// 用户输入:
		int choice = sc.nextInt();
		// 计算机随机数 1, 2, 3:
		int random = 1 + (int) Math.random() * 3;
		// TODO:
		switch (choice - random) {
			case 0 -> System.out.printf("你：%s, 系统：%s, 结果：平局", choice, random);
			case -1, 2 -> System.out.printf("你：%s, 系统：%s, 结果：赢", choice, random);
			case 1, -2 -> System.out.printf("你：%s, 系统：%s, 结果：输", choice, random);
			default -> System.out.printf("输入有误");
		}
	}

}