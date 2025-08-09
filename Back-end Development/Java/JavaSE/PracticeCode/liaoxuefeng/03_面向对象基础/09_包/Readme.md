## 请按如下包结构创建工程项目：
```shell
请按如下包结构创建工程项目：
oop-package
└── src
    └── com
        └── itranswarp
            ├── sample
            │   └── Main.java
            └── world
                └── Person.java
```
## 编译
```shell
javac -d .\bin (Get-ChildItem -Path .\src -Recurse -Filter *.java).FullName
```

## 运行
```shell
java -cp bin com.cm.sample.Main 
```