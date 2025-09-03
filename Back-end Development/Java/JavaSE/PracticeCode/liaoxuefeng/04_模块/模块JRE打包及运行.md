## 1、创建示例工程

工程目录如下：

```shell
\---oop-module
    +---bin
    \---src
        |   module-info.java
        |
        \---com
            \---cm
                \---sample
                        Greeting.java
                        Main.java
```

- module-info.java：模块的描述文件，内容如下

  ```java
  module hello.world {
  	requires java.base; // 可不写，任何模块都会自动引入java.base
  	requires java.xml;
  }
  ```

  > `module`：关键字，定义模块名称
  >
  > `requires xxx;`：表示这个模块需要引用的其他模块名。声明依赖关系后，才能使用引入的模块(除了java.base会自动被引入)。

- Greeting.java：模块内容，代码如下：

  ```java
  package com.cm.sample;
  
  public class Greeting {
  
  	public String hello(String name) {
  		return "Hello, " + name + "!";
  	}
  }
  ```

- Main.java：模块内容，代码如下：

  ```java
  package com.cm.sample;
  
  import javax.xml.XMLConstants;
  
  public class Main {
  
  	public static void main(String[] args) {
  		Greeting g = new Greeting();
  		System.out.println(g.hello(XMLConstants.XML_NS_PREFIX));
  	}
  
  }
  ```

## 2、编译模块

首先在命令行工具中切换目录到`oop-module`，在该目录下编译所有的`.java`文件到`bin`目录下，命令如下：

```shell
javac -d bin src/module-info.java src/com/cm/sample/*.java
```

编译成功后项目结构如下：

```shell
\---oop-module
    +---bin
    |   |   module-info.class
    |   |
    |   \---com
    |       \---cm
    |           \---sample
    |                   Greeting.class
    |                   Main.class
    |
    \---src
        |   module-info.java
        |
        \---com
            \---cm
                \---sample
                        Greeting.java
                        Main.java
```

把`bin`目录下的所有`.class`文件先打包成`jar`，在打包的时候，注意传入`--main-class`参数，让这个`jar`包能自己定位`main`方法所在的类：

```shell
jar --create --file hello.jar --main-class com.cm.sample.Main -C bin .
```

成功后在当前目录下生成`hello.jar`这个文件，运行：

```shell
java -jar hello.jar
Hello, xml!
```

把`jar`包转换成模块：

```shell
jmod create --class-path hello.jar hello.jmod
```

在当前目录得到了`hello.jomd`文件，尝试运行：

```shell
java --module-path hello.jmod --module hello.world
```

报错：

```shell
Error occurred during initialization of boot layer
java.lang.module.FindException: JMOD format not supported at execution time: hello.jmod
```

原因是`.jmod`不能被放入`--module-path`中。换成`.jar`就没问题了：

```shell
java --module-path hello.jar --module hello.world
Hello, xml!
```

> 过去发布一个Java应用程序，要运行它，必须下载一个完整的JRE，再运行jar包。而完整的JRE块头很大，有100多M。怎么给JRE瘦身呢？
>
> 为了支持模块化，Java 9首先带头把自己的一个巨大无比的`rt.jar`拆成了几十个`.jmod`模块，原因就是，运行Java程序的时候，实际上我们用到的JDK模块，并没有那么多。不需要的模块，完全可以删除。
>
> 现在，JRE自身的标准库已经分拆成了模块，只需要带上程序用到的模块，其他的模块就可以被裁剪掉。
>
> JOMD即是用来打包JRE的

## 3、打包JRE

```shell
jlink --module-path hello.jmod --add-modules java.base,java.xml,hello.world --output jre/
```

- `--module-path`：指定模块
- `--add-modules`：指定用到的模块
- `--output`：输出目录

成功后生成`jre`目录：一个完整的并且带有我们自己`hello.jmod`模块的JRE，运行：

```shell
jre/bin/java --module hello.world
Hello, xml!
```

> 要分发我们自己的Java应用程序，只需要把这个`jre`目录打个包给对方发过去，对方直接运行上述命令即可，既不用下载安装JDK，也不用知道如何配置我们自己的模块，极大地方便了分发和部署。

## 注：访问权限

class的这些访问权限只在一个模块内有效，模块和模块之间，例如，a模块要访问b模块的某个class，必要条件是b模块明确地导出了可以访问的包。

举个例子：我们编写的模块`hello.world`用到了模块`java.xml`的一个类`javax.xml.XMLConstants`，我们之所以能直接使用这个类，是因为模块`java.xml`的`module-info.java`中声明了若干导出：

```java
module java.xml {
    exports java.xml;
    exports javax.xml.catalog;
    exports javax.xml.datatype;
    ...
}
```

只有它声明的导出的包，外部代码才被允许访问。换句话说，如果外部代码想要访问我们的`hello.world`模块中的`com.itranswarp.sample.Greeting`类，我们必须将其导出：

```java
module hello.world {
    exports com.itranswarp.sample;

    requires java.base;
	requires java.xml;
}
```

因此，模块进一步隔离了代码的访问权限。