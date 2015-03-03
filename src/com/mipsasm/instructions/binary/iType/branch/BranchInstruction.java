package com.mipsasm.instructions.binary.iType.branch;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.BinaryInstruction;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class BranchInstruction extends BinaryInstruction{

	public BranchInstruction(String code, String binCode) {
		super(code, binCode);
	}

	@Override
	public String getHelp() {
		return super.getHelp() + "<rcomp1> <rcomp2> <tag>/<inmm>";
	}

	@Override
	public LineOfCode parseArguments(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {

	//los registros aqu� van al rev�s ya que el registro destino (el primero que se
	//escribe) queremos que vaya arriba y no abajo para ser el minuendo
	Register a = Register.getReg(code.readToken());
	Register b = Register.getReg(code.readToken());
	
	return LineOfCode.createITypeLineOfCode(	this.binCode, 
				b,
				a, 
				code.readToken(), 
				code,
				true,
				code.getCurrentLine());
	}
}
