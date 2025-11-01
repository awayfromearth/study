package com.itranswarp.learnjava;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
*   编译及运行：
*       javac -d ./bin -cp ".\lib\logback-classic-1.2.3.jar;.\lib\logback-core-1.2.3.jar;.\lib\slf4j-api-1.7.26.jar" .\src\com\itranswarp\learnjava\Main.java
*
*       java -cp ".;.\lib\logback-classic-1.2.3.jar;.\lib\logback-core-1.2.3.jar;.\lib\slf4j-api-1.7.26.jar;.\bin" com.itranswarp.learnjava.Main
*
*   注：要将 logback.xml 配置文件放在 bin 目录下
* */
public class Main {
    static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Start process {}...", Main.class.getName());

        try {
            "".getBytes("invalidCharsetName");
        } catch(UnsupportedEncodingException e) {
            // TODO: 使用log.error(String, Throwable)打印异常
            log.error("Invalid encoding !", e);
        }

        log.info("Process end .");
    }
}