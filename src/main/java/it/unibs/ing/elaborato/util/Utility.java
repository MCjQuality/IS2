package it.unibs.ing.elaborato.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe di utilita' contente metodi statici con diverse funzionalita'.
 */
public class Utility {

	public static String appendHorizontalLine(int length)
	{
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < length; i++)
		{
			buffer.append("─");
		}
		return buffer.toString();
	}

	public static <T> T checkCondition(String textToPrint, String errorMessage, Predicate<T> condition, Scanner scanner)
	{	
		T toReturn;
		
		System.out.print(textToPrint);
		do
		{
			toReturn = (T) scanner.nextLine();
			if(condition.test(toReturn))
				System.out.print(errorMessage);
		} while (condition.test(toReturn));

		return toReturn;
	}

	public static <T> T check2Condition(String textToPrint, String errorMessage1, String errorMessage2, Predicate<T> condition1, Predicate<T> condition2, Scanner scanner)
	{	
		T toReturn;
		
		System.out.print( textToPrint );
		do
		{
			toReturn = (T) scanner.nextLine();
			if(condition1.test((T) toReturn))
				System.out.print(errorMessage1);
			else if(condition2.test((T) toReturn))
				System.out.print(errorMessage2);
		} while(condition1.test((T) toReturn) || condition2.test((T) toReturn));

		return toReturn;
	}

	public static boolean isPresent(String filepath, String textToFind)
	{
		try
		{
			Scanner scanner = new Scanner(new File(filepath));

			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();

				if(line.equalsIgnoreCase(textToFind))
					return true;
			}
			scanner.close();
			return false;

		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public static void clearConsole(int millis) 
	{
		try
		{
			final String os = System.getProperty("os.name");

			Thread.sleep(millis);
			if (os.contains("Windows")) {
				// Se il sistema operativo è Windows
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				// Se il sistema operativo è Unix o Linux (o Mac OS X)
				new ProcessBuilder("clear").inheritIO().start().waitFor();
			}
		} catch (final Exception e) {
			System.out.println("Errore durante la pulizia della console: " + e.getMessage());
		}
	}

	public static boolean isPswValid(char [] psw, int digits_requirement, int letters_requirement)
	{
		int digits = 0;
		int letters = 0;
		for(char c : psw)
		{
			if(Character.isLetter(c))
				letters++;
			else if (Character.isDigit(c))
				digits++;
		}
		return digits >= digits_requirement && letters >= letters_requirement;
	}

	public static String addBlank(String string, int n)
	{
		int numSpacesToAdd = n - string.length();

		if (numSpacesToAdd > 0)
		{
			string += " ".repeat(numSpacesToAdd);
		}

		return string;
	}

	public static boolean isInt(String input)
	{
		try
		{
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isDouble(String input)
	{
		try
		{
			Double.parseDouble(input);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	public static boolean isValidEmail(String email)
	{
		Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
