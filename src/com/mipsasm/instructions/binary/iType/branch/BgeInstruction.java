package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.instructions.binary.InstructionWords;

public class BgeInstruction extends BranchInstruction {

	public BgeInstruction() {
		super(InstructionWords.CMD_BGE, InstructionWords.CMD_BIN_BGE);
	}

}
