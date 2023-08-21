package app.xutility;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xstring {
	public static int countOccurrencesOf(String input, String target) {
		return input.length() - input.replace(target, "").length();
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	public static boolean isNumeric(Character chr) { 
		try {  
			Double.parseDouble(chr.toString());  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
	public static String SubstringLastMatchOf_regex(String string, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find())
		{
			int numberOfGroups = matcher.groupCount();
		    return matcher.group(numberOfGroups);
		} else {
			return "";
		}
	}
	public static String firstMatchOf_regex(String string, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		ArrayList<String> foundGroups = new ArrayList<String>();
		
		while (matcher.find()) {
			foundGroups.add(matcher.group(matcher.groupCount()));
        }
		if (foundGroups.size() != 0)
		{
		    return foundGroups.get(foundGroups.size() - 1);
		} else {
			return "";
		}
	}
	public static boolean regexFind(String string, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean regexFindExcluded(String string, String regex, String excluded_regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find() || !(Pattern.compile(excluded_regex).matcher(matcher.group(0)).find()) ) {
			return true;
		} else {
			return false;
		}
	}

}
	