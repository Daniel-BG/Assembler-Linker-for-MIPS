package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class OriInstruction extends ITypeInstruction {

	public OriInstruction() {
		super(InstructionWords.CMD_ORI, InstructionWords.CMD_BIN_ORI);
	}

}
