package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class AndiInstruction extends ITypeInstruction {

	public AndiInstruction() {
		super(InstructionWords.CMD_ANDI, InstructionWords.CMD_BIN_ANDI);
	}

}
