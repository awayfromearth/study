public class Main {

    public static void main(String[] args) {
        // TODO: 给Person类增加一个静态字段count和静态方法getCount，统计实例的个数
        Person p1 = new Person("小明");
        System.out.println(Person.getCount()); // 1
        Person p2 = new Person("小红");
        System.out.println(Person.getCount()); // 2
        Person p3 = new Person("小军");
        System.out.println(Person.getCount()); // 3
    }

}

class Person {
    public static int count = 0;
    private String name;

    public Person(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }
}