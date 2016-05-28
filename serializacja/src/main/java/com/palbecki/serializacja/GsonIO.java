package com.palbecki.serializacja;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

public class GsonIO {
	static final String FILE_NAME = "serialized.object";

	public void save(Object object) throws IOException {
		FileOutputStream fOut = new FileOutputStream(FILE_NAME);
		OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
		myOutWriter.append(new Gson().toJson(object));
		myOutWriter.close();
		fOut.close();
	}
	
	public Object load(Class<?> classs) throws IOException {
		FileInputStream fIn = new FileInputStream(FILE_NAME);
		BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
		String aDataRow = "";
		String aBuffer = "";
		while ((aDataRow = myReader.readLine()) != null) 		{
			aBuffer += aDataRow ;
		}
		myReader.close();
		return new Gson().fromJson(aBuffer, classs);
	}
}
