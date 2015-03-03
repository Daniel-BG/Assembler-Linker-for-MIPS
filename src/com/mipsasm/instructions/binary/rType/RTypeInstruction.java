package com.mipsasm.instructions.binary.rType;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.BinaryInstruction;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.util.exceptions.*;

public abstract class RTypeInstruction extends BinaryInstruction {
	@Override
	public String getHelp() {
		return super.getHelp() + "<rdest> <rsource1> <rsource2>";
	}

	@Override
	protected LineOfCode parseArguments(Code code)
			throws InvalidInstructionException, InvalidRegisterException,
			OutOfBinaryRangeException, EndOfCodeException {
				
		return LineOfCode.createRTypeLineOfCode(	this.binCode, 
														Register.getReg(code.readToken()), 
														Register.getReg(code.readToken()), 
														Register.getReg(code.readToken()),
														code.getCurrentLine());
		
	}

	public RTypeInstruction(String code, String binCode) {
		super(code, binCode);
	}

	
}
