package main.java.gui;

import java.awt.Color;
import java.lang.reflect.Field;

public class GuiResource
{
// -------------------------------------------------
// Graphical parameters
// -------------------------------------------------
	public static final String		frame_title							= "Software particule truc bidule...";
																							// Dimensions
	public static final int			frame_width_initial					= 800;
	public static final int			frame_height_initial				= 700;
																							// Partition
	public static final double		frame_partitionWidth				= 7/10.;			//		Horizontal division of the frame
	public static final double		frame_partitionHeightLeft			= 8./10;			//		Vertical division of the frame
	public static final double		frame_partitionHeightRightTop		= 3./10;			//		Vertical division of the frame (top part)
	public static final double		frame_partitionHeightRightMiddle	= 3./10;			//		Vertical division of the frame (middle part)
	public static final double		frame_partitionHeightRight			= frame_partitionHeightRightTop + frame_partitionHeightRightMiddle;
//TODO	public static final int		frame_secureH		= 55;
	public static final boolean		frame_organizerEnabled				= false;
	public static final int			frame_organizerSize					= 5;
																							// Color of sub panels
	public static final Color		panelDraw_colorBG					= Color.cyan;
	public static final Color		panelBeta_colorBG					= Color.red;
	public static final Color		panelZoom_colorBG					= Color.magenta;
	public static final Color		panelcenterSet_colorBG				= Color.blue;
	public static final Color		panelButton_colorBG					= Color.green;

																							// Panel button
	public static final int			panelButton_nbrButton				= nbrAttributes("panelButton_buttonLabel");
	public static final String		panelButton_buttonLabel_StartPause	= "Start / Pause";
	public static final String		panelButton_buttonLabel_Reinit		= "Reinit";
	public static final String		panelButton_buttonLabel_Clear		= "Clear";
	public static final String		panelButton_buttonLabel_ReinitBounds= "Reinit bounds";


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