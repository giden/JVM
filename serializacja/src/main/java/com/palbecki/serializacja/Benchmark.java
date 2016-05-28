package com.palbecki.serializacja;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Benchmark {

    static final String name = "ala";
    static final String password = "lala";

    protected static void run() {
        boolean warmup = true;

        for (int j = 0; j < 10; j++) {
            if (j == 9)
                warmup = false;

            one(new Login(name, password), "\n============\njeden obiekt: ", warmup);

            List<Login> list = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                list.add(generateLogin());
            }
            JaxbList<Login> jaxblist = new JaxbList<>(list);
            one(jaxblist, "\n=================\nLista 10 obiektów: ", warmup);

            list = new ArrayList<>();

            for (int i = 0; i < 10000; i++) {
                list.add(generateLogin());
            }

            jaxblist = new JaxbList<>(list);
            one(jaxblist, "\n======================\nLista 10 tys. obiektów: ", warmup);
        }
    }

    private static Login generateLogin() {
        return new Login(RandomStringUtils.randomAscii(32), RandomStringUtils.randomAscii(32));
    }

    private static <T> void one(T object, String prefix, boolean warmup) {
        try {
            if (!warmup) {
                System.out.println(prefix);
            }
            SerializeObj(object, warmup);

            withJackson(object, warmup);

            withGson(object, warmup);

            withJAXB(object, warmup);

        } catch (JAXBException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static <T> void SerializeObj(T object, boolean warmup) throws IOException, ClassNotFoundException {
        long start;
        T login;
        Serialize serialize = new Serialize();
        start = System.nanoTime();
        serialize.serializeObject(object);
        login = (T) serialize.deserializeObject();
        info(warmup, System.nanoTime() - start, login, "Serializacja");
    }


    private static <T> void withJackson(T object, boolean warmup) throws IOException {
        long start;
        T login;
        start = System.nanoTime();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("serialized.object"), object);
        login = (T) mapper.readValue(new File("serialized.object"), object.getClass());
        info(warmup, System.nanoTime() - start, login, "Jackson2");
    }

    private static <T> void withGson(T object, boolean warmup) throws IOException {
        long start;
        T login;
        GsonIO gio = new GsonIO();
        start = System.nanoTime();
        gio.save(object);
        login = (T) gio.load(object.getClass());
        info(warmup, System.nanoTime() - start, login, "GSON");
    }

    private static <T> void withJAXB(T object, boolean warmup) throws JAXBException {
        long start;
        T login;
        JAXBHelper jaxb = new JAXBHelper();
        start = System.nanoTime();
        jaxb.save(object);
        login = (T) jaxb.load(object.getClass());
        info(warmup, System.nanoTime() - start, login, "JAX-B");
    }

    private static void info(boolean warmup, long time, Object login, String method) {
        if (!warmup) {
            System.out.printf("\n%-15s: %s ns\n", method, time);

            if (login instanceof Login) {
                System.out.print("Equals test: \n\t");
                System.out.print(((Login) login).username.equals(name));
                System.out.print(" ");
                System.out.print(((Login) login).password.equals(password));
                System.out.println();
            }
        }
    }
}
