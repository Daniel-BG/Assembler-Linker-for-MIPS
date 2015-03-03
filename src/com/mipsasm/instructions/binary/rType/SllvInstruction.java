package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class SllvInstruction extends RTypeInstruction{

	public SllvInstruction() {
		super(InstructionWords.CMD_SLLV, InstructionWords.CMD_BIN_SLLV);
	}

}
