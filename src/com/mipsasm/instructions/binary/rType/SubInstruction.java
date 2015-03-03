package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class SubInstruction extends RTypeInstruction {

	public SubInstruction() {
		super(InstructionWords.CMD_SUB, InstructionWords.CMD_BIN_SUB);
	}

}
