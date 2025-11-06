import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        String name = "Xiao Ming";
        int age = 20;
        Person p = new Person();

        // TODO: 利用反射给name和age字段赋值:
        Class c = p.getClass();
        Field f_name = c.getDeclaredField("name");
        f_name.setAccessible(true);
        Field f_age = c.getDeclaredField("age");
        f_age.setAccessible(true);

        f_name.set(p, "Xiao Ming");
        f_age.set(p, 20);

        System.out.println(p.getName()); // "Xiao Ming"
        System.out.println(p.getAge()); // 20
    }
}

class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}