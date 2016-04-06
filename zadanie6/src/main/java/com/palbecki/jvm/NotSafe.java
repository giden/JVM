package com.palbecki.jvm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Paweł Albecki!
 */
public class NotSafe {
    public static void Main() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Runnable run = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        sdf.parse("2010-10-10");
                    } catch (ParseException e) {
                        System.out.println("Problem przy parsowaniu: " + e.getMessage());
                    }
                }
            }
        };

        executorService.execute(run);
        executorService.execute(run);

        executorService.shutdown();
    }
}
