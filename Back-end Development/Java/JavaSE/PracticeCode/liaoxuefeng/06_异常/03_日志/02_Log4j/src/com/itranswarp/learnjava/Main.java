package com.itranswarp.learnjava;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
*   使用 commons-logging 编译及运行：
*       javac -d ./bin -cp .\lib\commons-logging-1.2.jar .\src\com\itranswarp\learnjava\Main.java
*
*       java -cp ".;.\lib\commons-logging-1.2.jar;.\bin" com.itranswarp.learnjava.Main
*
*   使用 Log4j 编译及运行：
*       javac -d ./bin -cp ".\lib\commons-logging-1.2.jar;.\lib\log4j-api-2.11.2.jar;.\lib\log4j-core-2.11.2.jar;.\lib\log4j-jcl-2.11.2.jar" .\src\com\itranswarp\learnjava\Main.java
*
*       java -cp ".;.\lib\commons-logging-1.2.jar;.\lib\log4j-api-2.11.2.jar;.\lib\log4j-core-2.11.2.jar;.\lib\log4j-jcl-2.11.2.jar;.\bin" com.itranswarp.learnjava.Main
*
*   注：要将 log4j2.xml 配置文件放在 bin 目录下
* */
public class Main {
    static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        log.info("Start process...");

        try {
            "".getBytes("invalidCharsetName");
        } catch(UnsupportedEncodingException e) {
            log.error("Invalid encoding !", e);
        }

        log.info("Process end .");
    }
}