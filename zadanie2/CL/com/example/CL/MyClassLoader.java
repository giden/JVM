package com.example.CL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MyClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (name.endsWith("ServiceImpl")) {
			return findClass(name);
		}

		return super.loadClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = null;
		try {
			bytes = loadClassData(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Class<?> result = defineClass(name, bytes, 0, bytes.length);
		return result;

		// return super.findClass(name);
	}

	private byte[] loadClassData(String name) throws IOException {
		String url = "http://localhost:8080/REST/JVM/getClass";
		URL myUrl = new URL(url);
		URLConnection connection = myUrl.openConnection();
		InputStream input = connection.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int data = input.read();

		while (data != -1) {
			buffer.write(data);
			data = input.read();
		}

		input.close();

		byte[] classData = buffer.toByteArray();
		return classData;
	}

}
