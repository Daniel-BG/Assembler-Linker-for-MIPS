package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class OutInstruction extends ITypeInstruction {

	public OutInstruction() {
		super(InstructionWords.CMD_OUT, InstructionWords.CMD_BIN_OUT);
	}

}
