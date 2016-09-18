package main.java.gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import main.java.app.App;
import main.java.gui.GuiResource;
import main.java.util.DoubleFormated;






@SuppressWarnings("serial")
public class PanelCenterSet extends JPanel
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	private App				app;
	private JTextPane		textPanelX;
	private JTextPane		textPanelY;
	private JCheckBox		checkBoxClear;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public PanelCenterSet(App app)
	{
		super();

		this.app = app;

		JPanel	panel		= new JPanel();
		JPanel	panelX		= new JPanel();
		JPanel	panelY		= new JPanel();
		JPanel	panelClear	= new JPanel();
		JLabel	labelX		= new JLabel(GuiResource.panelCenterSet_Label_positionX);
		JLabel	labelY		= new JLabel(GuiResource.panelCenterSet_Label_positionY);
		JLabel	labelClear	= new JLabel(GuiResource.panelCenterSet_Label_clear);
		this.textPanelX 	= new JTextPane();
		this.textPanelY 	= new JTextPane();
		this.checkBoxClear	= new JCheckBox();

		panelX		.setLayout(new BoxLayout(panelX,	BoxLayout.LINE_AXIS));	panelX		.add(labelX,	Component.LEFT_ALIGNMENT);	panelX		.add(this.textPanelX,	Component.RIGHT_ALIGNMENT);
		panelY		.setLayout(new BoxLayout(panelY,	BoxLayout.LINE_AXIS));	panelY		.add(labelY,	Component.LEFT_ALIGNMENT);	panelY		.add(this.textPanelY,	Component.RIGHT_ALIGNMENT);
		panelClear	.setLayout(new BoxLayout(panelClear,BoxLayout.LINE_AXIS));	panelClear	.add(labelClear,Component.LEFT_ALIGNMENT);	panelClear	.add(this.checkBoxClear,Component.RIGHT_ALIGNMENT);

		panelX		.setBorder(new EmptyBorder(GuiResource.panelCenterSet_marginLabelTop, GuiResource.panelCenterSet_marginLabelLeft, GuiResource.panelCenterSet_marginLabelBottom, GuiResource.panelCenterSet_marginLabelRight));
		panelY		.setBorder(new EmptyBorder(GuiResource.panelCenterSet_marginLabelTop, GuiResource.panelCenterSet_marginLabelLeft, GuiResource.panelCenterSet_marginLabelBottom, GuiResource.panelCenterSet_marginLabelRight));
		panelClear	.setBorder(new EmptyBorder(GuiResource.panelCenterSet_marginLabelTop, GuiResource.panelCenterSet_marginLabelLeft, GuiResource.panelCenterSet_marginLabelBottom, GuiResource.panelCenterSet_marginLabelRight));

		// Set font and style
		Font textPaneMousePosition = new Font(GuiResource.panelCenterSet_textPanelFontName_MousePosition, GuiResource.panelCenterSet_textPanelFontType_MousePosition, GuiResource.panelCenterSet_textPanelFontSize_MousePosition);
		this.textPanelX.setFont(textPaneMousePosition);
		this.textPanelY.setFont(textPaneMousePosition);
		this.textPanelX.setEditable(false);
		this.textPanelY.setEditable(false);
		StyledDocument docX = this.textPanelX.getStyledDocument();
		StyledDocument docY = this.textPanelY.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
		docX.setParagraphAttributes(0, docX.getLength(), center, false);
		docY.setParagraphAttributes(0, docY.getLength(), center, false);

		this.checkBoxClear.setSelected(app.isCenterSetClear());
//TODO		this.checkBoxClear.addComponentListener();

		this.setBackground(GuiResource.panelCenterSet_colorBG);
		this.setLayout(new GridBagLayout());
		this.setBorder(new EmptyBorder(GuiResource.panelCenterSet_marginTop, GuiResource.panelCenterSet_marginLeft, GuiResource.panelCenterSet_marginBottom, GuiResource.panelCenterSet_marginRight));

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(new TitledBorder(BorderFactory.createLineBorder(GuiResource.panelZoom_colorBorder), GuiResource.panelCenterSet_mainLabel));
		panel.add(panelX,		Component.LEFT_ALIGNMENT);
		panel.add(panelY,		Component.LEFT_ALIGNMENT);
		panel.add(panelClear,	Component.CENTER_ALIGNMENT);
		this.add(panel);

		this.setMouseExited();
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public void setMousePosition(double x, double y)
	{
		String strX = DoubleFormated.format(x, GuiResource.panelCenterSet_mousePositionCharNbr, GuiResource.panelCenterSet_mousePosition_forceScientist);
		String strY = DoubleFormated.format(y, GuiResource.panelCenterSet_mousePositionCharNbr, GuiResource.panelCenterSet_mousePosition_forceScientist);

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

		for (int i=1; i<=GuiResource.panelCenterSet_mousePositionCharNbr; i++)
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
