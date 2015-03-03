package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.instructions.binary.InstructionWords;

public class BltInstruction extends BranchInstruction {

	public BltInstruction() {
		super(InstructionWords.CMD_BLT, InstructionWords.CMD_BIN_BLT);
	}

}
