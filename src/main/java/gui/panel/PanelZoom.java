package main.java.gui.panel;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import main.java.gui.Gui;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_button;







@SuppressWarnings("serial")
public class PanelZoom extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private Gui				gui;

	private ButtonGroup		groupButtonZoom;
	private JRadioButton	radioButtonZoomIn;
	private JRadioButton	radioButtonZoomOut;
	private JCheckBox		checkBoxShowZoomPanel;
	private JButton			buttonZoomFactor;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelZoom(Gui gui) throws NoSuchMethodException, SecurityException
	{
		super();

		this.gui	= gui;

		this.radioButtonZoomIn	= new JRadioButton(GuiResource.panelZoom_radiobuttonLabel_ZoomIn);
		this.radioButtonZoomIn.setSelected(gui.isZoomInSelected());
//TODO		this.radioButtonZoomIn.addActionListener();

		this.radioButtonZoomOut	= new JRadioButton(GuiResource.panelZoom_radiobuttonLabel_ZoomOut);
		this.radioButtonZoomOut.setSelected(!gui.isZoomInSelected());
//TODO		this.radioButtonZoomOut.addActionListener();

		this.groupButtonZoom = new ButtonGroup();
		this.groupButtonZoom.add(this.radioButtonZoomIn);
		this.groupButtonZoom.add(this.radioButtonZoomOut);


		JLabel labelShowZoomPanel = new JLabel(GuiResource.panelZoom_Label_showZoomPanel);
		this.checkBoxShowZoomPanel = new JCheckBox();
		this.checkBoxShowZoomPanel.setSelected(GuiResource.frame_panelDrawZoom_initialShow);
		this.checkBoxShowZoomPanel.addActionListener(new EventListener_button(gui, "setPanelZoomShown"));

		this.buttonZoomFactor = new JButton();
		this.buttonZoomFactor.addActionListener(new EventListener_button(this, "setZoomFactor"));
		this.buttonZoomFactor.setText(GuiResource.panelZoom_buttonLabel_zoomFactor + gui.getZoomFactor());
		Font textPaneZoomFactorFont = new Font(GuiResource.panelZoom_buttonFontName_zoomFactor, GuiResource.panelZoom_buttonFontType_zoomFactor, GuiResource.panelZoom_buttonFontSize_zoomFactor);
		this.buttonZoomFactor.setFont(textPaneZoomFactorFont);

		JPanel panel2				= new JPanel();
		JPanel panelZoomFactor		= new JPanel();	panelZoomFactor	.add(this.buttonZoomFactor);
		JPanel panelShowZoomPanel	= new JPanel();
		panelShowZoomPanel.setLayout(new BoxLayout(panelShowZoomPanel, BoxLayout.LINE_AXIS));
		panelShowZoomPanel.add(labelShowZoomPanel,			Component.LEFT_ALIGNMENT);
		panelShowZoomPanel.add(this.checkBoxShowZoomPanel,	Component.RIGHT_ALIGNMENT);


		this.setBackground(GuiResource.panelZoom_colorBG);
		this.setLayout(new GridBagLayout());
		panel2.setBorder(new TitledBorder(BorderFactory.createLineBorder(GuiResource.panelZoom_colorBorder), GuiResource.panelZoom_mainLabel));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));

		this					.setBorder(new EmptyBorder(GuiResource.panelZoom_marginTop,		GuiResource.panelZoom_marginLeft,		GuiResource.panelZoom_marginBottom,		GuiResource.panelZoom_marginRight));
		this.radioButtonZoomIn	.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		this.radioButtonZoomOut	.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		panelShowZoomPanel		.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		panelZoomFactor			.setBorder(new EmptyBorder(GuiResource.panelZoom_marginLabelTop,GuiResource.panelZoom_marginLabelLeft,	GuiResource.panelZoom_marginLabelBottom,GuiResource.panelZoom_marginLabelRight));
		this.add(panel2);

		panel2.add(radioButtonZoomIn,	Component.LEFT_ALIGNMENT);
		panel2.add(radioButtonZoomOut,	Component.LEFT_ALIGNMENT);
		panel2.add(panelShowZoomPanel,	Component.CENTER_ALIGNMENT);
		panel2.add(panelZoomFactor,		Component.CENTER_ALIGNMENT);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void setZoomFactor()
	{
		String title = "title";
		String message = "message";
		double factor;
/* TODO
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


	public void setPanelZoomShown(boolean shown)
	{
		this.checkBoxShowZoomPanel.setSelected(shown);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}
