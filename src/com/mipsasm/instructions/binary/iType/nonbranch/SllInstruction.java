package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class SllInstruction extends ITypeInstruction {

	public SllInstruction() {
		super(InstructionWords.CMD_SLL, InstructionWords.CMD_BIN_SLL);
	}

}
