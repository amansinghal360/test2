package com.dp.qa.utils;

import java.time.Instant;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * @author Aman Singhal
 * This class is for generating random string havingvarious combination.
 *
 */
public class RandomUtil {
	Random rnd;

	public RandomUtil() {
		rnd = new Random();
	}

	public final static Logger log = Logger.getLogger(RandomUtil.class);

	private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String SPECIAL_CHAR = "!@#$%^&*";
	private static final String INT_CHAR = "0123456789";

	/**
	 * This method will return Random numbers depending on the provided size of the
	 * random number.
	 * 
	 * @param size
	 *            Size of the character length required
	 * @return random number of size provided
	 */
	public String getRandomNumber(int size) {
		log.info("Creating object of Random Class");
		StringBuilder sb = new StringBuilder(size);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i++)
			sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();

	}

	/**
	 * This method will return Random string depending on the provided size of the
	 * random string.
	 * 
	 * @param size
	 *            Size of the character length required
	 * @return random string of size provided
	 */
	public String getRandomString(int size) {

		log.info("Creating object of Random Class");
		StringBuilder sb = new StringBuilder(size);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i++)
			sb.append(LOWER_CASE.charAt(rnd.nextInt(LOWER_CASE.length())));
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();

	}

	/**
	 * This method will return String in caps depending on the provided size of the
	 * random number.
	 * 
	 * @param size
	 *            Size of the character length required
	 * @return random String all in capital letters of size provided
	 */
	public String getRandomStringAllCaps(int size) {

		log.info("Creating object of Random Class");
		StringBuilder sb = new StringBuilder(size);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i++)
			sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();

	}

	/**
	 * This method will return Random alpha numeric string depending on the provided
	 * size of the random number.
	 * 
	 * @param size
	 *            Size of the character length required
	 * @return Alpha numerical characters of the provided size
	 */
	public String getRandomAlphaNumeric(int size) {
		log.info("Creating object of Random Class");
		StringBuilder sb = new StringBuilder(size);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i = i + 1) {
			if (i % 2 == 0)
				sb.append(LOWER_CASE.charAt(rnd.nextInt(LOWER_CASE.length())));
			else
				sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
		}
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();
	}

	/**
	 * This method will return AlphaNumeric Values where characters will be in
	 * Capital
	 * 
	 * @param size
	 * @return Alphanumeric word
	 */
	public String getRandomAlphaNumericWithCaps(int size) {
		log.info("Creating object of Random Class");
		StringBuilder sb = new StringBuilder(size);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i = i + 1) {
			if (i % 2 == 0)
				sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
			else
				sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
		}
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();
	}

	/**
	 * This method will return AlphaNumeric Values where characters with the defined
	 * size of the String and numbers
	 * 
	 * @param sizeString
	 * @param sizeNumber
	 * @return Alpha numeric words with defined size
	 */
	public String getRandomAlphaNumeric(int sizeString, int sizeNumber) {
		log.info("Creating object of Random Class");
		int size = sizeNumber + sizeString;
		int stringCounter = 0;
		int numberCounter = 0;
		StringBuilder sb = new StringBuilder(sizeString + sizeNumber);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i = i + 1) {
			if (i % 2 == 0) {
				if (stringCounter != sizeString) {
					sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
					stringCounter++;
				} else {
					sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
					numberCounter++;
				}
			} else {
				if (numberCounter != sizeNumber) {
					sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
					numberCounter++;
				} else {
					sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
					stringCounter++;
				}
			}
		}
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();

	}

	/**
	 * This method will return AlphaNumeric with special characters
	 * 
	 * @param size
	 * @return Random Alpha Numeric With Special Character
	 */
	public String getRandomAlphaNumericWithSpecialCharacter(int size) {
		log.info("Creating object of Random Class");
		StringBuilder sb = new StringBuilder(size);
		log.info("Creating a string builder with size of Required new string");
		for (int i = 0; i < size; i = i + 1) {
			char choice = INT_CHAR.charAt(rnd.nextInt(3));
			System.out.println(choice);
			switch (choice) {
			case '0':
				sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
				break;
			case '1':
				sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
				break;
			case '2':
				sb.append(SPECIAL_CHAR.charAt(rnd.nextInt(SPECIAL_CHAR.length())));
				break;
			}
		}
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();
	}

	/**
	 * This method will return AlphaNumeric with special characters with defined
	 * size of string, numbers and special characters
	 * 
	 * @param sizeString
	 * @param sizeNumber
	 * @param sizeSpecialChar
	 * @return AlphaNumeric with special characters
	 */
	public String getRandomAlphaNumericWithSpecialCharacter(int sizeString, int sizeNumber, int sizeSpecialChar) {

		int size = sizeNumber + sizeSpecialChar + sizeString;
		int stringCounter = 0;
		int numberCounter = 0;
		int specialCharCounter = 0;
		StringBuilder sb = new StringBuilder(size);

		for (int i = 0; i < size; i = i + 1) {
			char choice = INT_CHAR.charAt(rnd.nextInt(3));
			System.out.println(choice);
			switch (choice) {
			case '0':
				if (stringCounter != sizeString) {
					sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
					stringCounter++;
					break;
				}
			case '1':
				if (numberCounter != sizeNumber) {
					sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
					numberCounter++;
					break;
				}

			case '2':
				if (specialCharCounter != sizeSpecialChar) {
					sb.append(SPECIAL_CHAR.charAt(rnd.nextInt(SPECIAL_CHAR.length())));
					specialCharCounter++;
					break;
				}
			default:
				if (stringCounter != sizeString) {
					sb.append(UPPER_CASE.charAt(rnd.nextInt(UPPER_CASE.length())));
					stringCounter++;
					break;
				}
				if (numberCounter != sizeNumber) {
					sb.append(INT_CHAR.charAt(rnd.nextInt(INT_CHAR.length())));
					numberCounter++;
					break;
				}
				if (specialCharCounter != sizeSpecialChar) {
					sb.append(SPECIAL_CHAR.charAt(rnd.nextInt(SPECIAL_CHAR.length())));
					specialCharCounter++;
					break;
				}
			}
		}
		log.info("Returning random generated string" + sb.toString());
		return sb.toString();

	}

	/**
	 * This method will return Random password with alpha numeric based on the size
	 * 
	 * @param size
	 *            of the password
	 * @return string as password
	 */
	public String getRandomPassword(int size) {
		log.info("Return alpha numeric password");
		return getRandomAlphaNumeric(size);
	}

	/**
	 * This method will return an email address with time stamp.
	 * 
	 * @return email with time stamp
	 */
	public String getEmailWithTimeStamp() {
		String email = "";
		long now = Instant.now().toEpochMilli();
		email = "TTN" + now + "yopmail.com";
		return email;
	}
}
