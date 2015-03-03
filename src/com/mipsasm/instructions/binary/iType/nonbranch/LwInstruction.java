package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class LwInstruction extends ITypeInstruction {

	public LwInstruction() {
		super(InstructionWords.CMD_LW, InstructionWords.CMD_BIN_LW);
	}
	
}
