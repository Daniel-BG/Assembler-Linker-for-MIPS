package com.mipsasm.instructions.binary.pseudoType;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.BinaryInstruction;
import com.mipsasm.instructions.binary.InstructionWords;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class NopInstruction extends BinaryInstruction{

	public NopInstruction() {
		super(InstructionWords.CMD_NOP, InstructionWords.CMD_BIN_ALU);
	}

	@Override
	protected LineOfCode parseArguments(Code code)
			throws InvalidInstructionException, InvalidRegisterException,
			OutOfBinaryRangeException, EndOfCodeException {
		// TODO Auto-generated method stub
		Register res = Register.getReg("zero");
		return LineOfCode.createRTypeLineOfCode(this.binCode, res, res, res, code.getCurrentLine());
	}

}
