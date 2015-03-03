package com.mipsasm.instructions.binary.rType;

import com.mipsasm.instructions.binary.InstructionWords;

public class XorInstruction extends RTypeInstruction {

	public XorInstruction() {
		super(InstructionWords.CMD_XOR, InstructionWords.CMD_BIN_XOR);
	}


}
