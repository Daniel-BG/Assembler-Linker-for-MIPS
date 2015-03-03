package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class AndInstruction extends RTypeInstruction {

	public AndInstruction() {
		super(InstructionWords.CMD_AND, InstructionWords.CMD_BIN_AND);
	}

}
