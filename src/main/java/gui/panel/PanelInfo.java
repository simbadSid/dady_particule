package main.java.gui.panel;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.java.gui.Gui;
import main.java.gui.GuiResource;
import main.java.gui.util.EventListener_button;
import main.java.util.DoubleFormated;







@SuppressWarnings("serial")
public class PanelInfo extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private JTextPane		textPanelX;
	private JTextPane		textPanelY;
	private JCheckBox		checkBoxClear;

	private ButtonGroup		groupButtonZoom;
	private JRadioButton	radioButtonZoomIn;
	private JRadioButton	radioButtonZoomOut;
	private JCheckBox		checkBoxShowZoomPanel;
	private JButton			buttonZoomFactor;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelInfo(Gui gui) throws NoSuchMethodException, SecurityException
	{
		super();

		this.setBackground(GuiResource.panelInfo_colorBG);
		this.setLayout(new GridLayout(1, 2, GuiResource.panelInfo_marginBetweenPanels_horizontal, GuiResource.panelInfo_marginBetweenPanels_vertical));
		this.setBorder(new EmptyBorder(GuiResource.panelInfo_marginTop, GuiResource.panelInfo_marginLeft, GuiResource.panelInfo_marginBottom, GuiResource.panelInfo_marginRight));

		// -------------------------------------------------
		// Panel initialPosition
		// -------------------------------------------------
		JPanel	panelInitialPosition	= new JPanel();
		JPanel	panelX					= new JPanel();
		JPanel	panelY					= new JPanel();
		JPanel	panelClear				= new JPanel();
		JLabel	labelX					= new JLabel(GuiResource.panelInfo_Label_positionX);
		JLabel	labelY					= new JLabel(GuiResource.panelInfo_Label_positionZ);
		JLabel	labelClear				= new JLabel(GuiResource.panelInfo_Label_clear);
		this.textPanelX 				= new JTextPane();
		this.textPanelY 				= new JTextPane();
		this.checkBoxClear				= new JCheckBox();

		panelX		.setLayout(new BoxLayout(panelX,	BoxLayout.LINE_AXIS));	panelX		.add(labelX,	Component.LEFT_ALIGNMENT);	panelX		.add(this.textPanelX,	Component.RIGHT_ALIGNMENT);
		panelY		.setLayout(new BoxLayout(panelY,	BoxLayout.LINE_AXIS));	panelY		.add(labelY,	Component.LEFT_ALIGNMENT);	panelY		.add(this.textPanelY,	Component.RIGHT_ALIGNMENT);
		panelClear	.setLayout(new BoxLayout(panelClear,BoxLayout.LINE_AXIS));	panelClear	.add(labelClear,Component.LEFT_ALIGNMENT);	panelClear	.add(this.checkBoxClear,Component.RIGHT_ALIGNMENT);

		panelX		.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop, GuiResource.panelInfo_marginLabelLeft, GuiResource.panelInfo_marginLabelBottom, GuiResource.panelInfo_marginLabelRight));
		panelY		.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop, GuiResource.panelInfo_marginLabelLeft, GuiResource.panelInfo_marginLabelBottom, GuiResource.panelInfo_marginLabelRight));
		panelClear	.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop, GuiResource.panelInfo_marginLabelLeft, GuiResource.panelInfo_marginLabelBottom, GuiResource.panelInfo_marginLabelRight));

		// Set font and style
		Font textPaneMousePosition = new Font(GuiResource.panelCenterSet_textPanelFontName_MousePosition, GuiResource.panelCenterSet_textPanelFontType_MousePosition, GuiResource.panelCenterSet_textPanelFontSize_MousePosition);
		this.textPanelX.setFont(textPaneMousePosition);		this.textPanelY.setFont(textPaneMousePosition);
		this.textPanelX.setEditable(false);					this.textPanelY.setEditable(false);
		StyledDocument docX = this.textPanelX.getStyledDocument();
		StyledDocument docY = this.textPanelY.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
		docX.setParagraphAttributes(0, docX.getLength(), center, false);
		docY.setParagraphAttributes(0, docY.getLength(), center, false);

		this.checkBoxClear.setSelected(gui.isCenterSetClear());
//TODO		this.checkBoxClear.addComponentListener();

		panelInitialPosition.setLayout(new BoxLayout(panelInitialPosition, BoxLayout.PAGE_AXIS));
		panelInitialPosition.setBorder(new TitledBorder(BorderFactory.createLineBorder(GuiResource.panelInfo_colorBorder), GuiResource.panelInfo_mainLabel_initialPosition));
		panelInitialPosition.add(panelX,		Component.LEFT_ALIGNMENT);
		panelInitialPosition.add(panelY,		Component.LEFT_ALIGNMENT);
		panelInitialPosition.add(panelClear,	Component.CENTER_ALIGNMENT);
		this.add(panelInitialPosition);
		this.setMouseExited();

		// -------------------------------------------------
		// Panel zoom
		// -------------------------------------------------
		this.radioButtonZoomIn	= new JRadioButton(GuiResource.panelInfo_radiobuttonLabel_ZoomIn);
		this.radioButtonZoomIn.setSelected(gui.isZoomInSelected());
//TODO		this.radioButtonZoomIn.addActionListener();

		this.radioButtonZoomOut	= new JRadioButton(GuiResource.panelInfo_radiobuttonLabel_ZoomOut);
		this.radioButtonZoomOut.setSelected(!gui.isZoomInSelected());
//TODO		this.radioButtonZoomOut.addActionListener();

		this.groupButtonZoom = new ButtonGroup();
		this.groupButtonZoom.add(this.radioButtonZoomIn);
		this.groupButtonZoom.add(this.radioButtonZoomOut);

		JLabel labelShowZoomPanel = new JLabel(GuiResource.panelInfo_Label_showZoomPanel);
		this.checkBoxShowZoomPanel = new JCheckBox();
		this.checkBoxShowZoomPanel.setSelected(GuiResource.frame_panelDrawZoom_initialShow);
		this.checkBoxShowZoomPanel.addActionListener(new EventListener_button(gui, "setPanelZoomShown"));

		this.buttonZoomFactor = new JButton();
		this.buttonZoomFactor.addActionListener(new EventListener_button(gui, "setZoomFactor"));
		this.buttonZoomFactor.setText(GuiResource.panelInfo_buttonLabel_zoomFactor + gui.getZoomFactor());

		JPanel panelZoom			= new JPanel();
		JPanel panelZoomFactor		= new JPanel();	panelZoomFactor	.add(this.buttonZoomFactor);
		JPanel panelShowZoomPanel	= new JPanel();
		panelShowZoomPanel.setLayout(new BoxLayout(panelShowZoomPanel, BoxLayout.LINE_AXIS));
		panelShowZoomPanel.add(labelShowZoomPanel,			Component.LEFT_ALIGNMENT);
		panelShowZoomPanel.add(this.checkBoxShowZoomPanel,	Component.RIGHT_ALIGNMENT);


		this.setBackground(GuiResource.panelInfo_colorBG);
		panelZoom.setBorder(new TitledBorder(BorderFactory.createLineBorder(GuiResource.panelInfo_colorBorder), GuiResource.panelInfo_mainLabel_zoom));
		panelZoom.setLayout(new BoxLayout(panelZoom, BoxLayout.PAGE_AXIS));

		this					.setBorder(new EmptyBorder(GuiResource.panelInfo_marginTop,		GuiResource.panelInfo_marginLeft,		GuiResource.panelInfo_marginBottom,		GuiResource.panelInfo_marginRight));
		this.radioButtonZoomIn	.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop,GuiResource.panelInfo_marginLabelLeft,	GuiResource.panelInfo_marginLabelBottom,GuiResource.panelInfo_marginLabelRight));
		this.radioButtonZoomOut	.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop,GuiResource.panelInfo_marginLabelLeft,	GuiResource.panelInfo_marginLabelBottom,GuiResource.panelInfo_marginLabelRight));
		panelShowZoomPanel		.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop,GuiResource.panelInfo_marginLabelLeft,	GuiResource.panelInfo_marginLabelBottom,GuiResource.panelInfo_marginLabelRight));
		panelZoomFactor			.setBorder(new EmptyBorder(GuiResource.panelInfo_marginLabelTop,GuiResource.panelInfo_marginLabelLeft,	GuiResource.panelInfo_marginLabelBottom,GuiResource.panelInfo_marginLabelRight));
		this.add(panelZoom);

		panelZoom.add(radioButtonZoomIn,	Component.LEFT_ALIGNMENT);
		panelZoom.add(radioButtonZoomOut,	Component.LEFT_ALIGNMENT);
		panelZoom.add(panelShowZoomPanel,	Component.CENTER_ALIGNMENT);
		panelZoom.add(panelZoomFactor,		Component.CENTER_ALIGNMENT);
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void setPanelZoomShown(boolean shown)
	{
		this.checkBoxShowZoomPanel.setSelected(shown);
	}


	public void setMousePosition(double x, double y)
	{
		String strX = DoubleFormated.format(x, GuiResource.panelInfo_mousePositionMaxCharNbr, GuiResource.panelInfo_mousePositionPrecision);
		String strY = DoubleFormated.format(y, GuiResource.panelInfo_mousePositionMaxCharNbr, GuiResource.panelInfo_mousePositionPrecision);

		if ((strX == null) || (strY == null))
		{
			this.setMouseExited();
		}
		else
		{
			this.textPanelX.setText(strX);
			this.textPanelY.setText(strY);
		}
	}


	public void setMouseExited()
	{
		String str = "";

		for (int i=1; i<=GuiResource.panelInfo_mousePositionMaxCharNbr; i++)
		{
//			if (i == GuiResource.panelCenterSet_mousePositionCharNbr/2)
//				str += "-";
//			else
				str += "X";
		}

		this.textPanelX.setText(str);
		this.textPanelY.setText(str);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}