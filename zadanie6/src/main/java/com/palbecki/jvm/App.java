package com.palbecki.jvm;

/**
 * @author Paweł Albecki!
 */
public class App {
    public static void main(String[] args) {
        if(args.length>0 && "safe".equals(args[0]))
            SDFThreadLocal.Main();
        else
            NotSafe.Main();
    }
}
