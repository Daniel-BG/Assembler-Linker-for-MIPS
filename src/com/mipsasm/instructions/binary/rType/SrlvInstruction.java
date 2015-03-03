package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class SrlvInstruction extends RTypeInstruction{

	public SrlvInstruction() {
		super(InstructionWords.CMD_SRLV, InstructionWords.CMD_BIN_SRLV);
	}

}
