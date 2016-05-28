package com.palbecki.serializacja;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBHelper {

	static final String FILE_NAME = "serialized.object";
	
	public void save(Object object) throws JAXBException {
		JAXBContext contextA = JAXBContext.newInstance(object.getClass());
		Marshaller jaxbMarshaller = contextA.createMarshaller();
		jaxbMarshaller.marshal(object, new File(FILE_NAME));
	}
	
	public Object load(Class<?> classs) throws JAXBException {
		JAXBContext contextB = JAXBContext.newInstance(classs);
		Unmarshaller unmarshallerB = contextB.createUnmarshaller();

		return unmarshallerB.unmarshal(new File(FILE_NAME));

	}
}
