import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 *
 * @author liaoxuefeng
 */

/*
*   编译命令：javac -cp commons-logging-1.2.jar Main.java
*   运行命令：java -cp .;commons-logging-1.2.jar Main
*   powerShell：在powerSehll中要用""将.;commons-logging-1.2.jar包裹
* */
public class Main {

    static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        log.info("Start process...");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            // TODO: 使用log.error(String, Throwable)打印异常
            log.error(e.toString(), e);
        }
        log.info("Process end.");
    }
}