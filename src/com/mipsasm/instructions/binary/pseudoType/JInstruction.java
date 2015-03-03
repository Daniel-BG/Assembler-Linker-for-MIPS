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
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class JInstruction extends BinaryInstruction{

	public JInstruction() {
		super(InstructionWords.CMD_J, InstructionWords.CMD_BIN_BEQ); //esto es porque utilizamos beq zero zero tag
	}

	@Override
	protected LineOfCode parseArguments(Code code)
			throws InvalidInstructionException, InvalidRegisterException,
			OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {

		return LineOfCode.createITypeLineOfCode(	this.binCode, 
				Register.getReg("zero"),
				Register.getReg("zero"), 
				code.readToken(), 
				code,
				true,
				code.getCurrentLine());
				
	}
	
	@Override
	public String getHelp() {
		return super.getHelp() + "<tag>/<inmm>";
	}

}
