package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class MultInstruction extends RTypeInstruction {

	public MultInstruction() {
		super(InstructionWords.CMD_MULT, InstructionWords.CMD_BIN_MULT);
	}

}
