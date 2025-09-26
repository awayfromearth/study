/*
* 请使用StringJoiner构造一个SELECT语句：
* */
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
    }

    static String buildSelectSql(String table, String[] fields) {
        // TODO:
        StringJoiner joiner = new StringJoiner(", ", "SELECT ", " FROM employee");
        for (String field : fields) {
            joiner.add(field);
        }
        return joiner.toString();
    }
}
