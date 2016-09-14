package main.java.gui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.text.AttributedCharacterIterator.Attribute;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.app.App;
import main.java.gui.GuiResource;






@SuppressWarnings("serial")
public class PanelButton extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private JButton	buttonStartPause;
	private JButton buttonReinit;
	private JButton	buttonClear;
	private JButton buttonReinitBound;
	private App		app;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelButton(App app)
	{
		GridLayout layout = new GridLayout(GuiResource.panelButton_nbrButton, 1);

		this.setLayout(layout);
		this.app = app;

		this.buttonStartPause = new JButton(GuiResource.panelButton_buttonLabel_StartPause);

		this.buttonReinit = new JButton(GuiResource.panelButton_buttonLabel_Reinit);

		this.buttonClear = new JButton(GuiResource.panelButton_buttonLabel_Clear);

		this.buttonReinitBound = new JButton(GuiResource.panelButton_buttonLabel_ReinitBounds);

		this.add(this.buttonStartPause);
		this.add(this.buttonReinit);
		this.add(this.buttonClear);
		this.add(this.buttonReinitBound);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}
