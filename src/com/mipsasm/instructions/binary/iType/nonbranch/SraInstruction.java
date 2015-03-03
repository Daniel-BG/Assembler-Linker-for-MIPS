package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class SraInstruction extends ITypeInstruction {

	public SraInstruction() {
		super(InstructionWords.CMD_SRA, InstructionWords.CMD_BIN_SRA);
	}

}
