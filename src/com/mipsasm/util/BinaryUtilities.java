package com.mipsasm.util;

import com.mipsasm.util.exceptions.*;

public class BinaryUtilities {
	/**
	 * Turns input number "num" into its binary form and outputs a number of digits (less significant)
	 * specified by "digits"
	 * @param num
	 * @param digits
	 * @return
	 */
	public static String toBinString (int num, int digits) throws OutOfBinaryRangeException{
		String numString = Integer.toBinaryString(num);
		if (numString.length() < digits) {
			numString = BinaryUtilities.ponCeros(numString, digits - numString.length());
		} else if (numString.length() > digits) {
			throw new OutOfBinaryRangeException("Representación en binario del número excede los bits asignados para su salida");
		}
		return numString;
	}
	/**
	 * Igual que el anterior pero teniendo en cuenta el signo
	 * @param num
	 * @param digits
	 * @return
	 * @throws OutOfBinaryRangeException
	 */
	public static String toBinStringSigned (int num, int digits) throws OutOfBinaryRangeException {
		String numString = Integer.toBinaryString(num);
		if (num < 0) {
			numString = BinaryUtilities.chopToMin(numString);
			numString = BinaryUtilities.extendSign(numString, digits - numString.length());
		}
		if (numString.length() < digits) {
			numString = BinaryUtilities.ponCeros(numString, digits - numString.length());
		} else if (numString.length() > digits) {
			throw new OutOfBinaryRangeException("Representación en binario del número excede los bits asignados para su salida");
		}
		return numString;
	}
	/**
	 * Pasa un número metido en string a un número metido en string pero en binario
	 * @param num
	 * @param digits
	 * @return
	 * @throws NumberFormatException
	 * @throws OutOfBinaryRangeException
	 */
	public static String toBinString (String num, int digits) throws NumberFormatException, OutOfBinaryRangeException {
		return BinaryUtilities.toBinString(Integer.parseInt(num), digits);
	}
	/**
	 * Pasa un número metido en string a un número metido en string pero en binario
	 * @param num
	 * @param digits
	 * @return
	 * @throws NumberFormatException
	 * @throws OutOfBinaryRangeException
	 */
	public static String toBinStringSigned (String num, int digits) throws NumberFormatException, OutOfBinaryRangeException {
		return BinaryUtilities.toBinStringSigned(Integer.parseInt(num), digits);
	}
	/**
	 * Añade ceros al principio del número (tantos como indique zeros) 
	 * @param input
	 * @param zeros
	 * @return
	 */
	public static String ponCeros (String input, int zeros) {
		for (int i = 0; i < zeros; i++) {
			input = "0" + input;
		}
		return input;
	}
	/**
	 * Extiende el signo del número tantos bits como indica signBits
	 * @param input
	 * @param signBits
	 * @return
	 */
	public static String extendSign (String input, int signBits) {
		char sign = input.charAt(0);
		for (int i = 0; i < signBits; i++) {
			input = sign + input;
		}
		return input;
	}
	/**
	 * Recorta un número a su mínima representación (quitando ceros o unos de la izquierda)
	 * Si se envía null o "" saldrá 0
	 * El comportamiento al enviar algo distinto de una representación en binario es indeterminado
	 * @param s
	 * @return
	 */
	public static String chopToMin(String s) {
		if (s == null || s.length() == 0)
			s = "0";
		char sign = s.charAt(0);
		while (s.length() > 1 && s.charAt(1) == sign)
			s = s.substring(1, s.length());
		return s;
	}
}

