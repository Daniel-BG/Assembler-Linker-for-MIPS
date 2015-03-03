package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.InstructionWords;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class BalInstruction extends BranchInstruction{

	public BalInstruction() {
		super(InstructionWords.CMD_BAL, InstructionWords.CMD_BIN_BAL);
	}

	
	@Override
	public LineOfCode parseArguments(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {

	
	return LineOfCode.createITypeLineOfCode(	this.binCode, 
				Register.getReg("zero"),
				Register.getReg("zero"), 
				code.readToken(), 
				code,
				true,
				code.getCurrentLine());
	}
}
