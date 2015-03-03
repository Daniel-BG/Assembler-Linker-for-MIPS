package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class OrInstruction extends RTypeInstruction{

	public OrInstruction() {
		super(InstructionWords.CMD_OR, InstructionWords.CMD_BIN_OR);
	}

}
