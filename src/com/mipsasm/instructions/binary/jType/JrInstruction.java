package com.mipsasm.instructions.binary.jType;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.BinaryInstruction;
import com.mipsasm.instructions.binary.InstructionWords;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class JrInstruction extends BinaryInstruction {

	public JrInstruction() {
		super(InstructionWords.CMD_JR, InstructionWords.CMD_BIN_JR);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LineOfCode parseArguments(Code code)
			throws InvalidInstructionException, InvalidRegisterException,
			OutOfBinaryRangeException, EndOfCodeException {
		
		return LineOfCode.createJTypeLineOfCode(	this.binCode, 
														Register.getReg(code.readToken()), 
														null,
														code.getCurrentLine());
	}

	@Override
	public String getHelp() {
		return super.getHelp() + "<rsource>";
	}
}
