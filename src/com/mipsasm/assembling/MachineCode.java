package com.mipsasm.assembling;

import java.util.Iterator;
import java.util.Vector;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class MachineCode {
	private Vector<LineOfCode> binaryCode = new Vector<LineOfCode>();
	
	/**
	 * Añade una línea binaria al código final
	 * @param loc
	 */
	public void addLineOfCode(LineOfCode loc) {
		binaryCode.add(loc);
	}
	/**
	 * Devuelve todas las instrucciones línea a línea
	 * @return
	 */
	public String getAllCode() {
		String ret = "";
		Iterator<LineOfCode> it = binaryCode.iterator();
		while (it.hasNext()) {
			ret += it.next().getBitArray().getBinaryArray() + "\n";
		}
	
		return ret;
	}
	/**
	 * Devuelve tanto el código binario como la instrucción correspondiente
	 * @return
	 */
	public String getAllCmtCode() {
		String ret = "";
		Iterator<LineOfCode> it = binaryCode.iterator();
		while (it.hasNext()) {
			LineOfCode loc = it.next();
			ret += loc.getBitArrayAndCmd() + "\n";
		}
	
		return ret;
		
	}
	
	/**
	 * Postprocesa las etiquetas y demás para generar el código final
	 * @throws OutOfBinaryRangeException 
	 * @throws IllegalStateException 
	 */
	public void postProcess(Code code) throws IllegalStateException, OutOfBinaryRangeException {
		Iterator<LineOfCode> it = binaryCode.iterator();
		while (it.hasNext()) {
			it.next().processLineOfCode(code);
		}
	}
	
}
