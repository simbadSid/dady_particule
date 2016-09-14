package main.java.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Set;







public class EventListener_button implements ActionListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Object		targetObject;
	private Method		targetMethod;
	private Set<Object>	targetMethodParameter;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public EventListener_button(Object targetObject, Method targetMethod, Set<Object> targetMethodParameter)
	{
//TODO check that target method accepts exactly 1 parameters of type Set<Object>
		this.targetObject			= targetObject;
		this.targetMethod			= targetMethod;
		this.targetMethodParameter	= targetMethodParameter;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			this.targetMethod.invoke(this.targetObject, this.targetMethodParameter);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ExceptionPrinter.printError("Resizer: method execution error", true);
		}
	}
}
