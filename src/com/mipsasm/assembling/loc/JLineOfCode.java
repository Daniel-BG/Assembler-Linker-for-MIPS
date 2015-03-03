package com.mipsasm.assembling.loc;

import com.mipsasm.assembling.BitArray;
import com.mipsasm.assembling.input.Code;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class JLineOfCode extends LineOfCode {
	private String opcode;
	private String reg;
	private String inmm;
	
	@Override
	public void processLineOfCode(Code code) throws IllegalStateException, OutOfBinaryRangeException {
		if (reg == null)
			this.bitArray = new BitArray(opcode + "0000000000" + Code.getValueOrTag(inmm, 16, code, this.lineNumber));
		else //inmm == null
			this.bitArray = new BitArray(opcode + reg + "000000000000000000000");
	}

	/**
	 * Crea una línea de código tipo J
	 * reg e inmm pueden ser nulos (uno solo de los dos y solo uno)
	 * @param opcode
	 * @param rest
	 */
	public JLineOfCode (String opcode, String reg, String inmm, int lineNumber) {
		super(lineNumber);
		this.type = "J";
		if (reg == null && inmm == null || reg != null && inmm != null)
			throw new IllegalArgumentException("Sólo reg o inmm pueden ser nulos");
		this.opcode = opcode;
		this.reg = reg;
		this.inmm = inmm;
	}
}
