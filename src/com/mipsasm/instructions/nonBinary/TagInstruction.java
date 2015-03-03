package com.mipsasm.instructions.nonBinary;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.DuplicatedTagException;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Instruction;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.util.debugging.Debugger;
import com.mipsasm.util.exceptions.*;

public class TagInstruction extends Instruction{

	@Override
	public LineOfCode parse(Code code) throws InvalidInstructionException,
			InvalidRegisterException, OutOfBinaryRangeException,
			EndOfCodeException {
		String tag = code.getToken();
		if (!(tag.length() == 0) && !(tag == null) && tag.charAt(tag.length()-1) == ':') {
			try {
				code.setTagAtCurrentLine(tag.substring(0, tag.length()-1)); //quitamos los :
			} catch (DuplicatedTagException e) {
				Debugger.debug(e.getMessage(), 0);
				throw new InvalidInstructionException(e.getMessage());
			} 
			code.readToken();
		} else {
			throw new InvalidInstructionException(this.getClass().getName() + " No detectado el comando");
		}
		return null;
	}

	@Override
	public String getHelp() {
		return "[<tag>:]";
	}

}
