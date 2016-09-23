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
	public static final boolean		frame_panelInfo_zoomInitialSelected			= true;
	public static final double		frame_panelInfo_zoomInitialFactor			= 2.0;
	public static final boolean		frame_panelInfo_zoomInitialCenterSetClear	= true;
																										// Panel draw
	public static final Color		panelDraw_colorBG_show						= Color.black;
	public static final Color		panelDraw_colorBG_hide						= Color.red;

																										// Panel control
	public static final Color		panelControl_colorBG						= Color.green;			//		Colors
	public static final Color		panelControl_colorBorder					= Color.black;
	public static final Color		panelControl_colorButtonStart				= Color.GREEN;
	public static final Color		panelControl_colorButtonPause				= Color.RED;
	public static final String		panelControl_mainLabel						= "Run controller";		//		Label
	public static final String		panelControl_label_TetaMin					= "Teta min: %s degrees";
	public static final String		panelControl_label_TetaMax					= "Teta max: %s degrees";
	public static final int			panelControl_tetaCharPrecision				= 2;
	public static final int			panelControl_tetaCharMaxNbrChar				= 5;
	public static final String		panelControl_buttonLabel_SetTetaValue		= "Teta: %." + panelControl_tetaCharPrecision + "f degrees";
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

																										// Panel Info
	public static final Color		panelInfo_colorBG							= Color.blue;			//		Color
	public static final Color		panelInfo_colorBorder						= Color.black;
	public static final String		panelInfo_mainLabel_zoom					= "Zoom panel";			//		Label (zoom side)
	public static final String		panelInfo_radiobuttonLabel_ZoomIn			= "Zoom in";
	public static final String		panelInfo_radiobuttonLabel_ZoomOut			= "Zoom out";
	public static final String		panelInfo_buttonLabel_zoomFactor			= "Zoom factor: ";
	public static final String		panelInfo_Label_showZoomPanel				= "Show zoom";
	public static final String		panelInfo_mainLabel_initialPosition			= "Initial position";	//		Label (initial position side)
	public static final String		panelInfo_Label_positionX					= "Mouse X: ";
	public static final String		panelInfo_Label_positionZ					= "Mouse Z: ";
	public static final String		panelInfo_Label_clear						= "Clear at re-center";
	public static final int			panelInfo_marginTop							= 10;					//		Border margin
	public static final int			panelInfo_marginBottom						= 10;
	public static final int			panelInfo_marginLeft						= 10;
	public static final int			panelInfo_marginRight						= 10;
	public static final int			panelInfo_marginBetweenPanels_horizontal	= 5;
	public static final int			panelInfo_marginBetweenPanels_vertical		= 5;
	public static final int			panelInfo_marginLabelTop					= 5;
	public static final int			panelInfo_marginLabelBottom					= 5;
	public static final int			panelInfo_marginLabelLeft					= 5;
	public static final int			panelInfo_marginLabelRight					= 5;
	public static final String		panelCenterSet_textPanelFontName_MousePosition= "Arial";			//		Font
	public static final int			panelCenterSet_textPanelFontType_MousePosition= Font.TRUETYPE_FONT;
	public static final int			panelCenterSet_textPanelFontSize_MousePosition= 18;
	public static final int			panelInfo_mousePositionMaxCharNbr			= 5;
	public static final int			panelInfo_mousePositionPrecision			= 3;

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
