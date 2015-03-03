package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class NorInstruction extends RTypeInstruction {

	public NorInstruction() {
		super(InstructionWords.CMD_NOR, InstructionWords.CMD_BIN_NOR);
	}

}
