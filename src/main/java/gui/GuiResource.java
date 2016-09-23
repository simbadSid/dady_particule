package main.java.gui;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Field;

public class GuiResource
{
// -------------------------------------------------
// Graphical parameters
// -------------------------------------------------
	public static final String		frame_title									= "Particle tracker";
																										// Dimensions
	public static final int			frame_width_initial							= 1200;
	public static final int			frame_height_initial						= 900;
																										// Partition
	public static final double		frame_partitionWidth						= 7/10.;				//		Horizontal division of the frame
	public static final double		frame_partitionHeightLeft					= 8./10;				//		Vertical division of the frame
	public static final double		frame_partitionHeightRight					= 5./10;				//		Vertical division of the frame (top + middle part)
	public static final double		frame_partitionHeightRightTop				= 1./2;					//		Vertical division of the frame (top part compared to middle)
	public static final int			frame_secure								= 6;
	public static final boolean		frame_organizerEnabled						= false;
	public static final int			frame_organizerSize							= 5;
	public static final boolean		frame_panelDrawZoom_initialShow				= false;
	public static final boolean		frame_panelZoom_initialSelected				= true;
	public static final double		frame_panelZoom_initialFactor				= 2.0;
	public static final boolean		frame_panelZoom_initialCenterSetClear		= true;
																										// Panel draw
	public static final Color		panelDraw_colorBG_show						= Color.black;
	public static final Color		panelDraw_colorBG_hide						= Color.red;

																										// Panel control
	public static final Color		panelControl_colorBG						= Color.green;			//		Colors
	public static final Color		panelControl_colorButtonStart				= Color.GREEN;
	public static final Color		panelControl_colorButtonPause				= Color.RED;
	public static final String		panelControl_mainLabel						= "Initial value";		//		Label
	public static final String		panelControl_label_TetaMin					= "Teta min: %f";
	public static final String		panelControl_label_TetaMax					= "Teta max: %f";
	public static final String		panelControl_buttonLabel_SetTetaValue		= "Teta: %f";
	public static final String		panelControl_buttonLabel_Start				= "Start";
	public static final String		panelControl_buttonLabel_Pause				= "Pause";
	public static final String		panelControl_buttonLabel_Reinit				= "Reinit";
	public static final String		panelControl_buttonLabel_Clear				= "Clear";
	public static final String		panelControl_buttonLabel_ReinitBounds		= "Reinit bounds";
	public static final String		panelControl_buttonLabel_Exit				= "Exit";
	public static final int			panelControl_marginTop						= 10;					//		Border margin
	public static final int			panelControl_marginBottom					= 10;
	public static final int			panelControl_marginLeft						= 10;
	public static final int			panelControl_marginRight					= 10;
	public static final int			panelControl_marginLabelTop					= 5;
	public static final int			panelControl_marginLabelBottom				= 5;
	public static final int			panelControl_marginLabelLeft				= 5;
	public static final int			panelControl_marginLabelRight				= 5;

																										// Panel Zoom
	public static final Color		panelZoom_colorBG							= Color.blue;			//		Color
	public static final Color		panelZoom_colorBorder						= Color.black;
	public static final String		panelZoom_mainLabel							= "Zoom panel";			//		Label
	public static final String		panelZoom_radiobuttonLabel_ZoomIn			= "Zoom in";
	public static final String		panelZoom_radiobuttonLabel_ZoomOut			= "Zoom out";
	public static final String		panelZoom_buttonLabel_zoomFactor			= "Zoom factor: ";
	public static final String		panelZoom_Label_showZoomPanel				= "Show zoom panel";
	public static final String		panelZoom_buttonFontName_zoomFactor			= "Arial";				//		Font
	public static final int			panelZoom_buttonFontType_zoomFactor			= Font.TRUETYPE_FONT;
	public static final int			panelZoom_buttonFontSize_zoomFactor			= 18;
	public static final int			panelZoom_marginTop							= 15;					//		Border margin
	public static final int			panelZoom_marginBottom						= 15;
	public static final int			panelZoom_marginLeft						= 15;
	public static final int			panelZoom_marginRight						= 15;
	public static final int			panelZoom_marginLabelTop					= 5;
	public static final int			panelZoom_marginLabelBottom					= 5;
	public static final int			panelZoom_marginLabelLeft					= 5;
	public static final int			panelZoom_marginLabelRight					= 5;

																										// Panel CenterSet
	public static final Color		panelCenterSet_colorBG						= Color.blue;			//		Color
	public static final String		panelCenterSet_mainLabel					= "Initial position";	//		Label
	public static final String		panelCenterSet_Label_positionX				= "Mouse X: ";
	public static final String		panelCenterSet_Label_positionZ				= "Mouse Z: ";
	public static final String		panelCenterSet_Label_clear					= "Clear at re-center";
	public static final String		panelCenterSet_textPanelFontName_MousePosition= "Arial";			//		Font
	public static final int			panelCenterSet_textPanelFontType_MousePosition= Font.TRUETYPE_FONT;
	public static final int			panelCenterSet_textPanelFontSize_MousePosition= 18;
	public static final int			panelCenterSet_marginTop					= 15;					//		Border margin
	public static final int			panelCenterSet_marginBottom					= 15;
	public static final int			panelCenterSet_marginLeft					= 15;
	public static final int			panelCenterSet_marginRight					= 15;
	public static final int			panelCenterSet_marginLabelTop				= 5;
	public static final int			panelCenterSet_marginLabelBottom			= 5;
	public static final int			panelCenterSet_marginLabelLeft				= 5;
	public static final int			panelCenterSet_marginLabelRight				= 5;
	public static final int			panelCenterSet_mousePositionCharNbr			= 6;
	public static final boolean		panelCenterSet_mousePosition_forceScientist	= false;

																										// Frame Exit
	public static final String		frameExit_title								= "Exit";
	public static final String		frameExit_message							= "Save results before exit?";
	public static final String		frameExit_buttonLabel_Save					= "Save";
	public static final String		frameExit_buttonLabel_Discrad				= "Discard";
	public static final String		frameExit_buttonLabel_Cancel				= "Cancel";

																										// Frame Teta
	public static final String		frameTeta_title								= "Teta";
	public static final String		frameTeta_message							= "Teta value in degrees (teta in [%f, %f])";
	public static final String		frameTeta_messageError						= "Teta angle must be a real belonging to [%f, %f])";

																										// Frame Save
	public static final String		frameSave_title								= "Save results";

																										// Frame zoom error
	public static final String		frameZoomError								= "Zoom index exceeded";


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private static int nbrAttributes(String attributeStartName)
	{
		int res = 0;

		for (Field field: GuiResource.class.getDeclaredFields())
		{
			if (field.getName().startsWith(attributeStartName))
				res ++;
		}

		return res;
	}
}
