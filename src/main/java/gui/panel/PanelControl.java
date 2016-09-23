package main.java.gui.panel;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import main.java.app.App;
import main.java.gui.Gui;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_button;
import main.java.gui.util.EventListener_scrollBarSizeBarListenerAdjustment;
import main.java.util.AngleConverter;
import main.java.util.DoubleFormated;







@SuppressWarnings("serial")
public class PanelControl extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private App		app;
	private Gui		gui;
	private	JButton buttonStartPause;
	private JButton	buttonTetaValue;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelControl(App app, Gui gui) throws NoSuchMethodException, SecurityException
	{
		JPanel	panel			= new JPanel();
		JPanel	panelTop		= new JPanel();
		JPanel	panelMiddle		= new JPanel();
		JPanel	panelBottom		= new JPanel();
		String	tetaMinDegree	= DoubleFormated.format(AngleConverter.gradiantToDegree(App.tetaMin), GuiResource.panelControl_tetaCharMaxNbrChar, GuiResource.panelControl_tetaCharPrecision);
		String	tetaMaxDegree	= DoubleFormated.format(AngleConverter.gradiantToDegree(App.tetaMax), GuiResource.panelControl_tetaCharMaxNbrChar, GuiResource.panelControl_tetaCharPrecision);

		this.app = app;
		this.gui = gui;
		this.setBackground(GuiResource.panelControl_colorBG);
		this.setBorder(new EmptyBorder(GuiResource.panelControl_marginTop, GuiResource.panelControl_marginLeft, GuiResource.panelControl_marginBottom, GuiResource.panelControl_marginRight));
		this.setLayout(new GridLayout(1, 1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(new TitledBorder(BorderFactory.createLineBorder(GuiResource.panelControl_colorBorder), GuiResource.panelControl_mainLabel));
		JLabel labelTetaMin	= new JLabel(String.format(GuiResource.panelControl_label_TetaMin, tetaMinDegree));
		JLabel labelTetaMax	= new JLabel(String.format(GuiResource.panelControl_label_TetaMax, tetaMaxDegree));

		JScrollBar scrollBarTetaValue = new JScrollBar(JScrollBar.HORIZONTAL);
		double tetaValue = app.getTeta() / (App.tetaMax - App.tetaMin) * scrollBarTetaValue.getMaximum();
		scrollBarTetaValue.setBorder(new EmptyBorder(GuiResource.panelControl_marginLabelTop, GuiResource.panelControl_marginLabelLeft, GuiResource.panelControl_marginLabelBottom, GuiResource.panelControl_marginLabelRight));
		scrollBarTetaValue.setUnitIncrement(1);
		scrollBarTetaValue.setValue((int)tetaValue);
		scrollBarTetaValue.addAdjustmentListener(new EventListener_scrollBarSizeBarListenerAdjustment(gui, "changeAngle"));

//TODO		scrollBarTetaValue.addAdjustmentListener();
// Set the position of the scroll bar

		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.LINE_AXIS));
		panelTop.setBorder(new EmptyBorder(GuiResource.panelControl_marginLabelTop, GuiResource.panelControl_marginLabelLeft, GuiResource.panelControl_marginLabelBottom, GuiResource.panelControl_marginLabelRight));
		panelTop.add(labelTetaMin,		Component.LEFT_ALIGNMENT);
		panelTop.add(scrollBarTetaValue,Component.CENTER_ALIGNMENT);
		panelTop.add(labelTetaMax,		Component.RIGHT_ALIGNMENT);

		this.buttonTetaValue = new JButton(String.format(GuiResource.panelControl_buttonLabel_SetTetaValue, AngleConverter.gradiantToDegree(app.getTeta())));
		this.buttonTetaValue.addActionListener(new EventListener_button(gui, "changeAngle"));
		panelMiddle.add(this.buttonTetaValue);

		panelBottom.setBorder(new EmptyBorder(GuiResource.panelControl_marginLabelTop, GuiResource.panelControl_marginLabelLeft, GuiResource.panelControl_marginLabelBottom, GuiResource.panelControl_marginLabelRight));
		this.buttonStartPause = new JButton();
		this.buttonStartPause.addActionListener(new EventListener_button(gui, "startPause"));
		if (app.isRunning())
		{
			this.buttonStartPause.setText(GuiResource.panelControl_buttonLabel_Pause);
			this.buttonStartPause.setBackground(GuiResource.panelControl_colorButtonPause);
		}
		else
		{
			this.buttonStartPause.setText(GuiResource.panelControl_buttonLabel_Start);
			this.buttonStartPause.setBackground(GuiResource.panelControl_colorButtonStart);
		}

		JButton buttonReinit = new JButton(GuiResource.panelControl_buttonLabel_Reinit);
//TODO		buttonReinit.addActionListener();

		JButton buttonClear = new JButton(GuiResource.panelControl_buttonLabel_Clear);
//TODO		buttonClear.addActionListener();

		JButton buttonReinitBound = new JButton(GuiResource.panelControl_buttonLabel_ReinitBounds);
//TODO		buttonReinitBound.addActionListener();

		JButton buttonExit = new JButton(GuiResource.panelControl_buttonLabel_Exit);
		buttonExit.addActionListener(new EventListener_button(gui, "exit"));

		panelBottom.add(buttonStartPause);
		panelBottom.add(buttonReinit);
		panelBottom.add(buttonClear);
		panelBottom.add(buttonReinitBound);
		panelBottom.add(buttonExit);

		panel.add(panelTop);
		panel.add(panelMiddle,	Component.CENTER_ALIGNMENT);
//		this.add(scrollBarTetaValue, JComponent.TOP_ALIGNMENT);
//		this.add(panelMiddle);
		panel.add(panelBottom);
		this.add(panel);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void pause()
	{
		this.buttonStartPause.setText(GuiResource.panelControl_buttonLabel_Start);
		this.buttonStartPause.setBackground(GuiResource.panelControl_colorButtonStart);
	}


	public void start()
	{
		this.buttonStartPause.setText(GuiResource.panelControl_buttonLabel_Pause);
		this.buttonStartPause.setBackground(GuiResource.panelControl_colorButtonPause);
	}
	

	public void setTeta(double tetaGradiant)
	{
		this.buttonTetaValue.setText(String.format(GuiResource.panelControl_buttonLabel_SetTetaValue, tetaGradiant));
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}
