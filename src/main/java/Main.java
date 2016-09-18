package main.java;

import javax.swing.SwingUtilities;

import main.java.app.App;
import main.java.gui.Gui;
import main.java.gui.util.ExceptionPrinter;








public class Main implements Runnable
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	public static final String	welcomeMessage					= "Welcome to the \"Je c pas trop quoi\" software";


// -------------------------------------------------
// Main method
// -------------------------------------------------
	@Override
	public void run()
	{
		Gui gui = null;
		App app = null;

		try
		{
			app	= new App();
			gui	= new Gui(app);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if		(gui == null)	ExceptionPrinter.printError("Error while initializing the Graphic user interface",	true);
			else if	(app == null)	ExceptionPrinter.printError("Error while initializing the application",				true);
			else					ExceptionPrinter.printError("Uknown internal error",								true);
		}

		app.setGui(gui);
		System.out.println(welcomeMessage);
	}


	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Main());
	}
}
