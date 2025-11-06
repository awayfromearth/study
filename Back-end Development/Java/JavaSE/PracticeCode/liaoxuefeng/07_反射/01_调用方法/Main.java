import java.lang.reflect.Method;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 *
 * @author liaoxuefeng
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String name = "Xiao Ming";
        int age = 20;
        Person p = new Person();

        // TODO: 利用反射调用setName和setAge方法:
        Class<?> clz = p.getClass();
        Method mn = clz.getMethod("setName", String.class);
        mn.invoke(p, "Xiao Ming");
        Method ma = clz.getMethod("setAge", int.class);
        ma.invoke(p, 20);

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