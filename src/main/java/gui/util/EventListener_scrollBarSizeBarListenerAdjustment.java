package main.java.gui.util;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.lang.reflect.Method;






public class EventListener_scrollBarSizeBarListenerAdjustment implements AdjustmentListener
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Object		targetObject;
	private Method		targetMethod;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public EventListener_scrollBarSizeBarListenerAdjustment(Object targetObject, String methodName) throws NoSuchMethodException, SecurityException
	{
		this.targetObject			= targetObject;
		this.targetMethod			= targetObject.getClass().getMethod(methodName, double.class);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		int percentage = (int) (e.getValue() * 10. / 9.);

		try
		{
			this.targetMethod.invoke(this.targetObject, percentage);
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
			ExceptionPrinter.printError("ScrollBar_listener: method execution error", true);
		}
	}
}
