package main.java.gui.panel;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import main.java.app.App;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_button;
import main.java.gui.util.ExceptionPrinter;






@SuppressWarnings("serial")
public class PanelZoom extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private App				app;
	private ButtonGroup		groupButtonZoom;
	private JRadioButton	radioButtonZoomIn;
	private JRadioButton	radioButtonZoomOut;
	private JButton			buttonZoomFactor;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelZoom(App app) throws NoSuchMethodException, SecurityException
	{
		super();

		this.app				= app;

		this.radioButtonZoomIn	= new JRadioButton(GuiResource.panelZoom_radiobuttonLabel_ZoomIn);
		this.radioButtonZoomIn.setSelected(app.isZoomInSelected());
//TODO		this.radioButtonZoomIn.addActionListener();

		this.radioButtonZoomOut	= new JRadioButton(GuiResource.panelZoom_radiobuttonLabel_ZoomOut);
		this.radioButtonZoomOut.setSelected(!app.isZoomInSelected());
//TODO		this.radioButtonZoomOut.addActionListener();

		this.groupButtonZoom = new ButtonGroup();
		this.groupButtonZoom.add(this.radioButtonZoomIn);
		this.groupButtonZoom.add(this.radioButtonZoomOut);

		this.buttonZoomFactor = new JButton();
		this.buttonZoomFactor.addActionListener(new EventListener_button(this, this.getClass().getMethod("setZoomFactor"), null));
		this.buttonZoomFactor.setText(GuiResource.panelZoom_buttonLabel_zoomFactor + app.getZoomFactor());
		Font textPaneZoomFactorFont = new Font(GuiResource.panelZoom_buttonFontName_zoomFactor, GuiResource.panelZoom_buttonFontType_zoomFactor, GuiResource.panelZoom_buttonFontSize_zoomFactor);
		this.buttonZoomFactor.setFont(textPaneZoomFactorFont);

		JPanel panel			= new JPanel();
//		JPanel panelZoomIn		= new JPanel(); panelZoomIn 	.add(this.radioButtonZoomIn);
//		JPanel panelZoomOut		= new JPanel();	panelZoomOut	.add(this.radioButtonZoomOut);
		JPanel panelZoomFactor	= new JPanel();	panelZoomFactor	.add(this.buttonZoomFactor);

		this.setBackground(GuiResource.panelZoom_colorBG);
		this.setLayout(new GridBagLayout());
		panel.setBorder(new TitledBorder(BorderFactory.createLineBorder(GuiResource.panelZoom_colorBorder), GuiResource.panelZoom_mainLabel));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		this					.setBorder(new EmptyBorder(GuiResource.panelZoom_marginTop,		GuiResource.panelZoom_marginLeft,		GuiResource.panelZoom_marginBottom,		GuiResource.panelZoom_marginRight));
		this.radioButtonZoomIn	.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		this.radioButtonZoomOut	.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		panelZoomFactor			.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		this.add(panel);

		panel.add(radioButtonZoomIn,	Component.LEFT_ALIGNMENT);
		panel.add(radioButtonZoomOut,	Component.LEFT_ALIGNMENT);
		panel.add(panelZoomFactor,		Component.CENTER_ALIGNMENT);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void setZoomFactor()
	{
		String title = "title";
		String message = "message";
		double factor;
/*
		while (true)
		{
			String userChoice = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, null, null, this.app.getZoomFactor());
			try
			{
				factor = Double.parseDouble(userChoice);
				boolean test = this.app.setZoomFactor(factor);
				if (!test) throw new RuntimeException();
				return;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				String.parse(); this.add(comp);
				ExceptionPrinter.printError(message, false);
			}
		}
*/

	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}
