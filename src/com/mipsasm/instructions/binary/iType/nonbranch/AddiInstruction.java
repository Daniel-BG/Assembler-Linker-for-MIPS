package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class AddiInstruction extends ITypeInstruction {

	public AddiInstruction() {
		super(InstructionWords.CMD_ADDI, InstructionWords.CMD_BIN_ADDI);
	}

}
