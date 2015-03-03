package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.instructions.binary.InstructionWords;

public class SrlInstruction extends ITypeInstruction {

	public SrlInstruction() {
		super(InstructionWords.CMD_SRL, InstructionWords.CMD_BIN_SRL);
	}

}
