package main.java.gui.panel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import main.java.app.App;
import main.java.gui.Gui;
import main.java.gui.GuiResource;







public class SaveInterface
{
	/**
	 * Print a custom user interface to choose the directory where to save the current app state.</br>
	 * @return false if an error occurred or the user has canceled.
	 * @throws IOException 
	 */
	public static boolean save(App app, Gui gui) throws IOException
	{
		JFileChooser chooser = new JFileChooser(); 

		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(GuiResource.frameSave_title);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);									// disable the "All files" option

		int result = chooser.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION)
			return false;

		String	sessionId	= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss").format(new Date());
		String	filePathApp	= chooser.getSelectedFile().getAbsolutePath() + "/" + sessionId + "_app";
		String	filePathGui	= chooser.getSelectedFile().getAbsolutePath() + "/" + sessionId + "_gui";
		File	fileApp		= new File(filePathApp);
		File	fileGui		= new File(filePathGui);

		fileApp.createNewFile();
		fileGui.createNewFile();

		FileOutputStream fileOutputApp = new FileOutputStream(fileApp, false);
		FileOutputStream fileOutputGui = new FileOutputStream(fileGui, false);

		app.save(fileOutputApp);
		gui.save(fileOutputGui);
		return true;
	}
}