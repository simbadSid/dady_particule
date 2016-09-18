package main.java.gui.panel;

import java.awt.Component;
import java.awt.GridLayout;
import java.lang.reflect.Method;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import main.java.app.App;
import main.java.gui.Gui;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_button;







@SuppressWarnings("serial")
public class PanelControl extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private App		app;
	private Gui		gui;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelControl(App app, Gui gui) throws NoSuchMethodException, SecurityException
	{
		Method targetMethod;
		JPanel panelTop		= new JPanel();
		JPanel panelMiddle	= new JPanel();
		JPanel panelBottom	= new JPanel();

		this.app = app;
		this.gui = gui;
		this.setBackground(GuiResource.panelControl_colorBG);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(new EmptyBorder(GuiResource.panelControl_marginTop, GuiResource.panelControl_marginLeft, GuiResource.panelControl_marginBottom, GuiResource.panelControl_marginRight));

		panelTop.setBorder(new EmptyBorder(GuiResource.panelControl_marginLabelTop, GuiResource.panelControl_marginLabelLeft, GuiResource.panelControl_marginLabelBottom, GuiResource.panelControl_marginLabelRight));
		JLabel labelBetaMin	= new JLabel(String.format(GuiResource.panelControl_label_BetaMin, App.betaMin));
		JLabel labelBetaMax	= new JLabel(String.format(GuiResource.panelControl_label_BetaMax, App.betaMax));
		JButton buttonBetaValue = new JButton();
		buttonBetaValue.setText(GuiResource.panelControl_buttonLabel_SetBetaValue);
//TODO		buttonBetaValue.addActionListener();
		panelTop.add(new JLabel(),		Component.LEFT_ALIGNMENT);
		panelTop.add(buttonBetaValue,	Component.CENTER_ALIGNMENT);
		panelTop.add(new JLabel(),		Component.RIGHT_ALIGNMENT);
		
		JScrollBar scrollBarBetaValue = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBarBetaValue.setBorder(new EmptyBorder(GuiResource.panelControl_marginLabelTop, GuiResource.panelControl_marginLabelLeft, GuiResource.panelControl_marginLabelBottom, GuiResource.panelControl_marginLabelRight));
//TODO		scrollBarBetaValue.addAdjustmentListener();
		
		
		
		JButton buttonStartPause = new JButton(GuiResource.panelControl_buttonLabel_StartPause);
//TODO		buttonStartPause.addActionListener();

		JButton buttonReinit = new JButton(GuiResource.panelControl_buttonLabel_Reinit);
//TODO		buttonReinit.addActionListener();

		JButton buttonClear = new JButton(GuiResource.panelControl_buttonLabel_Clear);
//TODO		buttonClear.addActionListener();

		JButton buttonReinitBound = new JButton(GuiResource.panelControl_buttonLabel_ReinitBounds);
//TODO		buttonReinitBound.addActionListener();

		JButton buttonExit = new JButton(GuiResource.panelControl_buttonLabel_Exit);
		targetMethod = this.getClass().getDeclaredMethod("listenerExit");
		buttonExit.addActionListener(new EventListener_button(this, targetMethod, null));

		panelBottom.add(buttonStartPause);
		panelBottom.add(buttonReinit);
		panelBottom.add(buttonClear);
		panelBottom.add(buttonReinitBound);
		panelBottom.add(buttonExit);

		this.add(panelTop);
		this.add(panelMiddle);
		this.add(panelBottom);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void listenerExit()
	{
		String		message		= GuiResource.frameExit_message;
		Object[]	options		= {GuiResource.frameExit_buttonLabel_Save, GuiResource.frameExit_buttonLabel_Discrad, GuiResource.frameExit_buttonLabel_Cancel};
		int			userChoice	= JOptionPane.showOptionDialog(null,
									message,
									GuiResource.frameExit_title,
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									null ,     //do not use a custom Icon
									options,  //the titles of buttons
									options[0]); //default button title
		switch (userChoice)
		{
			case 0:
				boolean test = false;
				try					{SaveInterface.save(this.app, this.gui);}
				catch(Exception e)	{e.printStackTrace();}
				if (test)	break;
				else		return;
			case 1: break;
			case 2: return;
		}
		System.exit(0);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}
