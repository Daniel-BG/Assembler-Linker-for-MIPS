package com.mipsasm.instructions.binary.pseudoType;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.BinaryInstruction;
import com.mipsasm.instructions.binary.InstructionWords;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.util.exceptions.*;

public class LiInstruction extends BinaryInstruction {

	public LiInstruction() {
		super(InstructionWords.CMD_LI, InstructionWords.CMD_BIN_PSEUDO);
	}


	@Override
	protected LineOfCode parseArguments(Code code)
			throws InvalidInstructionException, InvalidRegisterException,
			OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {

		return LineOfCode.createITypeLineOfCode(InstructionWords.CMD_BIN_ADDI, 
														Register.getReg(code.readToken()), 
														Register.getReg("zero"), 
														code.readToken(), 
														code, 
														false,
														code.getCurrentLine());
		
	}
	
	@Override
	public String getHelp() {
		return super.getHelp() + "<rdest> <inmm>";
	}

}
