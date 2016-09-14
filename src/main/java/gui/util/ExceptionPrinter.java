package main.java.gui.util;

import javax.swing.JOptionPane;






public class ExceptionPrinter
{
	public static void printError(String message, boolean exit)
	{
		JOptionPane.showMessageDialog(null, message);
		if (exit)
			System.exit(0);
	}
}
