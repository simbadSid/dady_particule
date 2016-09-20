package main.java.gui.util;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.reflect.Method;








public class EventListener_resizer implements ComponentListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private static boolean errorDetected = false;	// Hack added to stop the triple error printing TODO: to remove

	private Object		targetObject;
	private Method		targetMethod;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public EventListener_resizer(Object targetObject, Method targetMethod)
	{
//TODO check that target method accepts exactly 2 parameters of type int
		this.targetObject	= targetObject;
		this.targetMethod	= targetMethod;
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public void componentResized(ComponentEvent event)
	{
		if (errorDetected) return;

		int width	= event.getComponent().getWidth();
		int height	= event.getComponent().getHeight();

		try
		{
			this.targetMethod.invoke(this.targetObject, width, height);
		}
		catch (Exception e)
		{
			errorDetected = true;
			e.printStackTrace();
			ExceptionPrinter.printError("Resizer: method execution error", true);
		}
	}


	@Override public void componentHidden(ComponentEvent e)	{/*System.out.println("Listener not implemented");*/}
	@Override public void componentMoved(ComponentEvent e)	{/*System.out.println("Listener not implemented");*/}
	@Override public void componentShown(ComponentEvent e)	{/*System.out.println("Listener not implemented");*/}
}
