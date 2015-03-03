package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class AddInstruction extends RTypeInstruction {
	public AddInstruction() {
		super(InstructionWords.CMD_ADD, InstructionWords.CMD_BIN_ADD);
	}
}
