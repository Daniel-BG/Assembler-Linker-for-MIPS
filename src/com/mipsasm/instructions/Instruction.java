package com.mipsasm.instructions;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;

import com.mipsasm.util.exceptions.*;
public abstract class Instruction {
	/**
	 * Coge y transforma la siguiente instrucci�n del c�digo en binario
	 * Si es instrucci�n que no da binario devolver� nulo
	 * @param code
	 * @return
	 * @throws InvalidInstructionException
	 * @throws InvalidRegisterException
	 * @throws OutOfBinaryRangeException
	 * @throws EndOfCodeException
	 * @throws UnsupportedTagException 
	 */
	public abstract LineOfCode parse(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException;

	public abstract String getHelp();
}
