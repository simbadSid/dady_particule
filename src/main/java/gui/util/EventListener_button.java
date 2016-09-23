package main.java.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;








public class EventListener_button implements ActionListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Object		targetObject;
	private Method		targetMethod;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public EventListener_button(Object targetObject, String methodName) throws NoSuchMethodException, SecurityException
	{
		this.targetObject			= targetObject;
		this.targetMethod			= targetObject.getClass().getMethod(methodName);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			this.targetMethod.invoke(this.targetObject);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ExceptionPrinter.printError("Button listener: method execution error", true);
		}
	}
}
