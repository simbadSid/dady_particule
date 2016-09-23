package main.java.util;







public class DoubleFormated
{
// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public static String format(double value, int maxNbrChar, int precision)
	{
		String format	= "%1." + precision + "f";
		String res = String.format(format, value);

//TODO scientific notation		if (res.length() > maxNbrChar)
//		{
			
//		}
		return res;
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
}