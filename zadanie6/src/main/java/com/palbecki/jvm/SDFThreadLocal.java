package com.palbecki.jvm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Paweł Albecki!
 */
public class SDFThreadLocal {

    private ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public Date parse(String dateString) throws ParseException {
        return df.get().parse(dateString);
    }

    public static void Main() {
        final SDFThreadLocal tl = new SDFThreadLocal();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable run = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        tl.parse("2010-10-10");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.execute(run);
        executorService.execute(run);

        executorService.shutdown();
    }
}