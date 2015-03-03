package com.mipsasm.instructions.binary.iType.nonbranch;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.BinaryInstruction;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.util.exceptions.*;

public class ITypeInstruction extends BinaryInstruction{

	public ITypeInstruction(String code, String binCode) {
		super(code, binCode);
	}

	@Override
	public LineOfCode parseArguments(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {

		
		return LineOfCode.createITypeLineOfCode(	this.binCode, 
				Register.getReg(code.readToken()),
				Register.getReg(code.readToken()), 
				code.readToken(), 
				code,
				false,
				code.getCurrentLine());
 
	}
	
	@Override
	public String getHelp() {
		return super.getHelp() + "<rdest> <rsource> <inmm>";
	}
	

}
