package com.binge.smallmvc.state;

/**
 * @author binge
 * @datetime 2017年10月17日
 * @version
 * @encoding UTF8
 * @Description
 */

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.connect();
        context.register();
    }
}
