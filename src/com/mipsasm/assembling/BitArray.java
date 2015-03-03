package com.mipsasm.assembling;
import com.mipsasm.util.*;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class BitArray {
	public static int charToBit( char c ) {
		if (c == '0')
			return 0;
		else if (c == '1')
			return 1;
		throw new IllegalArgumentException("Input char is not 0 or 1");
	}
	
	public static int bitArrayStringToInt( String bits ) {
		if (bits.length() != 32) {
			throw new IllegalArgumentException("Bit array input string must be 32 chars long");
		}
		int resultado = 0;
		for (int i = 0; i < 32; i++) {
			resultado = (resultado << 1) + BitArray.charToBit(bits.charAt(i));
		}
		return resultado;
	}
	
	
	private int bits;
	public BitArray (String bits) {
		this.bits = BitArray.bitArrayStringToInt(bits);
	}
	
	public String getBinaryArray() {
		try {
			return BinaryUtilities.toBinString(this.bits, 32);
		} catch (OutOfBinaryRangeException e) {
			//nunca se da este caso por la construcción de la clase
			return "ERROR@BitArray";
		}
	}
	
	public int getIntValue() {
		return this.bits;
	}
	
	public String getHexArray() {
		return Integer.toHexString(this.bits);
	}
}
