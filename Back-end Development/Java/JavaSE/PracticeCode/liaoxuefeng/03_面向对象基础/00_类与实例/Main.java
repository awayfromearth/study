/*
* 请定义一个City类，该class具有如下字段:

name: 名称，String类型
latitude: 纬度，double类型
longitude: 经度，double类型
实例化几个City并赋值，然后打印。
* */
public class Main {
    public static void main(String[] args) {
        City bj = new City();
        bj.name = "Beijing";
        bj.latitude = 39.903;
        bj.longitude = 116.401;
        System.out.println(bj.name);
        System.out.println("location: " + bj.latitude + ", " + bj.longitude);
    }
}

class City {
    String name;
    double latitude;
    double longitude;
}
