package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class XoriInstruction extends ITypeInstruction {

	public XoriInstruction() {
		super(InstructionWords.CMD_XORI, InstructionWords.CMD_BIN_XORI);
	}

}
