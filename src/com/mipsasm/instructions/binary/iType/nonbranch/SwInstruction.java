package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class SwInstruction extends ITypeInstruction{

	public SwInstruction() {
		super(InstructionWords.CMD_SW, InstructionWords.CMD_BIN_SW);
	}

}
