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

public class MovInstruction extends BinaryInstruction {

	public MovInstruction() {
		super(InstructionWords.CMD_MOV, InstructionWords.CMD_BIN_ADD);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LineOfCode parseArguments(Code code)
			throws InvalidInstructionException, InvalidRegisterException,
			OutOfBinaryRangeException, EndOfCodeException {

		return LineOfCode.createRTypeLineOfCode(this.binCode, 
														Register.getReg(code.readToken()), 
														Register.getReg(code.readToken()), 
														Register.getReg("zero"),
														code.getCurrentLine());
		
	}
	
	@Override
	public String getHelp() {
		return super.getHelp() + "<rdest> <rsource>";
	}

}
