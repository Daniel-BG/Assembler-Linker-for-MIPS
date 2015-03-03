package com.mipsasm.assembling;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.InstructionParser;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;

import com.mipsasm.util.debugging.*;
import com.mipsasm.util.exceptions.*;

public class Assembler {

	public static String assemble(String inputCode, boolean detailed) throws Exception {
		Debugger.debug("Starting assembly", 0);
		Code code = new Code(inputCode);
		MachineCode resultado = new MachineCode();
		InstructionParser parser = new InstructionParser();
		
		Debugger.debug("Assembling code", 0);
		while (true) {
			try {
				LineOfCode loc = parser.parseInstruction(code);
				String cmd = code.getAndResetCommand();
				if (loc != null) {
					loc.setInstruction(cmd);
					Debugger.debug("Added: " + loc.getInstruction(), 2);
					resultado.addLineOfCode(loc);
				}
			} catch (InvalidInstructionException | InvalidRegisterException
					| OutOfBinaryRangeException | UnsupportedTagException e) {
				Debugger.debug("Error near: [" + code.getLastToken() + "] @ [" + code.getAndResetCommand() + "]" + " loc: " + code.getCurrentLine() , 0);
				throw e;
			} catch (EndOfCodeException e) {
				Debugger.debug(e.getMessage(), 1);
				break;
			}
		}
		Debugger.debug("Assembling succesful", 0);

		Debugger.debug("Linking code", 0);
		resultado.postProcess(code);
		Debugger.debug("Linking succesful", 0);

		
		if (!detailed)
			return resultado.getAllCode();
		else
			return resultado.getAllCmtCode();
	}

}

	