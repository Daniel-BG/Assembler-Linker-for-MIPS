package com.mipsasm.assembling.loc;

import com.mipsasm.assembling.BitArray;
import com.mipsasm.assembling.input.Code;
import com.mipsasm.util.BinaryUtilities;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class ILineOfCode extends LineOfCode{
	private String opcode;
	private String bitsrs;
	private String bitsrd;
	private String inmm;
	private boolean canHaveTag;
	
	
	@Override
	public void processLineOfCode(Code code) throws IllegalStateException, OutOfBinaryRangeException {
		if (canHaveTag)
			this.bitArray = new BitArray(opcode + bitsrs + bitsrd + Code.getValueOrTag(inmm, 16, code, this.lineNumber));
		else
			this.bitArray = new BitArray(opcode + bitsrs + bitsrd + BinaryUtilities.toBinStringSigned(inmm, 16));
	}

	/**
	 * Crea una línea de código del tipo I
	 * @param opcode
	 * @param bitsrd
	 * @param bitsrs
	 * @param inmm
	 */
	public ILineOfCode (String opcode, String bitsrd, String bitsrs, String inmm, boolean canHaveTag,int lineNumber) {
		super(lineNumber);
		this.type = "I";
		this.opcode = opcode;
		this.bitsrd = bitsrd;
		this.bitsrs = bitsrs;
		this.inmm = inmm;
		this.canHaveTag = canHaveTag;
	}

}
