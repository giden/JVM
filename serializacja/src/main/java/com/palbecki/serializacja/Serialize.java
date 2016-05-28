package com.palbecki.serializacja;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {

	static final String FILE_NAME = "serialized.object";

	public void serializeObject(Object object) throws FileNotFoundException, IOException {

		ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(FILE_NAME));
		out.writeObject(object);
		out.close();

	}
	public Object deserializeObject() throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(FILE_NAME));

		Object result = in.readObject();

		in.close();

		return result;
	}

}
