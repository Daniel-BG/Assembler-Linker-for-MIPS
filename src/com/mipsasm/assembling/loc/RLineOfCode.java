package com.mipsasm.assembling.loc;

import com.mipsasm.assembling.BitArray;
import com.mipsasm.assembling.input.Code;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class RLineOfCode extends LineOfCode{
	private String opcode;
	private String bitsrs;
	private String bitsrt;
	private String bitsrd;
	private String shamt;
	private String funct;

	/**
	 * Crea una línea de código del tipo R
	 * @param opcode
	 * @param bitsrd
	 * @param bitsrs
	 * @param bitsrt
	 * @param shamt
	 * @param funct
	 */
	public RLineOfCode (String opcode, String bitsrd, String bitsrs, String bitsrt, String shamt, String funct, int lineNumber) {
		super(lineNumber);
		this.type = "R";
		this.opcode = opcode;
		this.bitsrs = bitsrs;
		this.bitsrt = bitsrt;
		this.bitsrd = bitsrd;
		this.shamt = shamt;
		this.funct = funct;
	}
	
	@Override
	public void processLineOfCode(Code code) throws IllegalStateException, OutOfBinaryRangeException {
		this.bitArray = new BitArray(opcode + bitsrs + bitsrt + bitsrd + shamt + funct);
	}
}
