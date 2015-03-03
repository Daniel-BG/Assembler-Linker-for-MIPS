package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.instructions.binary.InstructionWords;

public class BeqInstruction extends BranchInstruction {

	public BeqInstruction() {
		super(InstructionWords.CMD_BEQ, InstructionWords.CMD_BIN_BEQ);
	}

}
