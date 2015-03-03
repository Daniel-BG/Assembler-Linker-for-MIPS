package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.instructions.binary.InstructionWords;

public class BgtInstruction extends BranchInstruction {

	public BgtInstruction() {
		super(InstructionWords.CMD_BGT, InstructionWords.CMD_BIN_BGT);
	}

}
