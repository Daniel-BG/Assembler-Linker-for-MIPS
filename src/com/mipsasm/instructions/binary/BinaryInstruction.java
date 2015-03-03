package com.mipsasm.instructions.binary;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.Instruction;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.util.exceptions.*;

public abstract class BinaryInstruction extends Instruction{
	@Override
	public String getHelp() {
		return this.strCode + "\t";
	}
	
	
	protected String strCode;
	protected String binCode;
	public BinaryInstruction(String code, String binCode) {
		this.strCode = code;
		this.binCode = binCode;
	}
	
	/**
	 * Coge y transforma la siguiente instrucci�n del c�digo en binario
	 * @param code
	 * @return
	 * @throws InvalidInstructionException
	 * @throws InvalidRegisterException
	 * @throws OutOfBinaryRangeException
	 * @throws EndOfCodeException
	 * @throws UnsupportedTagException 
	 */
	public LineOfCode parse(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {
		if (code.checkToken(this.strCode)) {
			LineOfCode loc = this.parseArguments(code);
			loc.setTag(code.getCurrentLineTag());
			code.incrementLine();
			return loc;
		}
		throw new InvalidInstructionException(this.getClass().getName() + " No detectado el comando");
	}
	
	/**
	 * Procesa los argumentos sabiendo ya que es la instrucción concreta
	 * Si falla alguno no intenta con otra instrucción ya que sabe que deberían
	 * haber funcionado los actuales.
	 * @param code
	 * @return
	 * @throws InvalidInstructionException
	 * @throws InvalidRegisterException
	 * @throws OutOfBinaryRangeException
	 * @throws EndOfCodeException
	 * @throws UnsupportedTagException 
	 */
	protected abstract LineOfCode parseArguments(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException;

}
