package main.java.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;







public class ObjectResourceConverter
{
	/**
	 * Creates an object of class targetClass by parsing the XML file at the given path.</br>
	 * Uses the java native resource converter (JAXBContext).
	 */
	public static Object instantiate(String resourcePath, Class<?> targetClass)
	{
		try
		{
	
			File file = new File(resourcePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(targetClass);
	
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return jaxbUnmarshaller.unmarshal(file);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * Stores the object targetObject within the resource XML file at the given path.</br>
	 * Uses the java native resource converter (JAXBContext).
	 */
	public static void storeInResource(String resourcePath, Object targetObject)
	{
		try
		{
			File file = new File(resourcePath);
			JAXBContext jaxbContext = JAXBContext.newInstance(targetObject.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(targetObject, file);

		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
	}
}