package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.instructions.binary.InstructionWords;

public class BneInstruction extends BranchInstruction {

	public BneInstruction() {
		super(InstructionWords.CMD_BNE, InstructionWords.CMD_BIN_BNE);
	}

}
