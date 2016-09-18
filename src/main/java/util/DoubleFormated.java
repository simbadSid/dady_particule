package main.java.util;







public class DoubleFormated
{
// -------------------------------------------------
// Public methods
// -------------------------------------------------
	public static String format(double value, int nbrChar, boolean forceScientistNotation)
	{
		String res = Double.toString(value);

//TODO scientific notation		if ((res.length() > nbrChar) || (forceScientistNotation))
//		{
			
//		}

		return appendSpace(res, nbrChar);
	}


// -------------------------------------------------
// Private methods
// -------------------------------------------------
	private static String appendSpace(String value, int nbrChar)
	{
		if (value.length() > nbrChar) return null;

		String res = new String(value);

		for (int i=value.length(); i<=nbrChar; i++)
		{
			res = " " + res;
		}

		return res;
	}
}