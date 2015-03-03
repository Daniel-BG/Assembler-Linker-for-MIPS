package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.instructions.binary.InstructionWords;

public class BleInstruction extends BranchInstruction {

	public BleInstruction() {
		super(InstructionWords.CMD_BLE, InstructionWords.CMD_BIN_BLE);
	}

}
