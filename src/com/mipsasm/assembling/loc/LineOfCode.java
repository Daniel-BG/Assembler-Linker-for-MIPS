package com.mipsasm.assembling.loc;

import com.mipsasm.assembling.BitArray;
import com.mipsasm.assembling.input.Code;
import com.mipsasm.instructions.Register;
import com.mipsasm.instructions.binary.InstructionWords;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.util.BinaryUtilities;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public abstract class LineOfCode {
	protected BitArray bitArray = null;
	protected String type;
	protected String instruction;
	protected String tag;
	protected int lineNumber;
	
	public LineOfCode(int lineNumber) {
		this.lineNumber = lineNumber;
	}
		
	/**
	 * Procesa la línea de código para ponerle el valor de la etiqueta y demás
	 * @param code
	 * @throws IllegalStateException
	 * @throws OutOfBinaryRangeException
	 */
	public abstract void processLineOfCode(Code code) throws IllegalStateException, OutOfBinaryRangeException;
	
	/**
	 * Pone el valor de la instrucción que generó el código que contiene este objeto
	 * al valor indicado por s
	 * @param s
	 */
	public void setInstruction(String s) {
		this.instruction = s;
	}
	/**
	 * Pone un valor a tag
	 * @param s
	 */
	public void setTag(String s) {
		this.tag = s;
	}
	/**
	 * Devuelve los 32 bits de la instrucción
	 * @return
	 */
	public BitArray getBitArray() {
		if (bitArray == null)
			throw new IllegalStateException("Instruction not yet processed");
		return this.bitArray;
	}
	/**
	 * Devuelve el tipo (si existe) de la instrucción (I;J;R..)
	 * si no devolverá null
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	/**
	 * Devuelve la instrucción que generó esta línea de ceros y unos
	 * @return
	 */
	public String getInstruction() {
		return this.instruction;
	}
	/**
	 * Devuelve la etiqueta de la línea o null si no tiene
	 * @return
	 */
	public String getTag() {
		return this.tag;
	}
	/**
	 * Devuelve tanto los 32 bits binarios como la instrucción que los generó
	 * @return
	 */
	public String getBitArrayAndCmd() {
		return 	this.getBitArray().getBinaryArray() + " " +
				this.getLineNumber() + "\t " + 
				this.instruction + (this.tag == null ? "": " [" + this.tag + "]");
	}
	/**
	 * Devuelve el número de línea que ocupa esta línea dentro del código
	 * @return
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	/**
	 * Crea una línea de código del tipo I
	 * @param opCode
	 * @param rd
	 * @param rs
	 * @param ifield
	 * @param code (usado para si hay tag)
	 * @param mayHaveTag (si puede tener inmediato o tag)
	 * @return
	 * @throws OutOfBinaryRangeException
	 * @throws UnsupportedTagException 
	 */
	public static LineOfCode createITypeLineOfCode(String opCode, Register rd, Register rs, String ifield, Code code, boolean mayHaveTag, int lineNumber) throws OutOfBinaryRangeException, UnsupportedTagException {
		if (!mayHaveTag) {
			try {
				Integer.parseInt(ifield);
			} catch (NumberFormatException e) {
				throw new UnsupportedTagException("El valor \"" + ifield + "\" debe ser un número");
			}
			
		}
		return new ILineOfCode(	opCode,
				rd.getRegCode(),
				rs.getRegCode(),
				ifield,
				mayHaveTag,
				lineNumber);
	}

	/**
	 * Crea una línea de código del tipo R con shift
	 * @param funct
	 * @param rd
	 * @param rs
	 * @param shamtf
	 * @return
	 * @throws OutOfBinaryRangeException
	 */
	public static LineOfCode createRTypeSLineOfCode(String funct, Register rd, Register rs, String shamtf, int lineNumber) throws OutOfBinaryRangeException {
		try {
			String shamt = BinaryUtilities.toBinString(Integer.parseInt(shamtf), 5);
			return new RLineOfCode(	InstructionWords.CMD_BIN_ALU,
					rd.getRegCode(),
					rs.getRegCode(),
					"00000",
					shamt,
					funct,
					lineNumber);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Number expected");
		} 
	}

	/**
	 * Crea una línea de código de tipo R sin shift
	 * @param funct
	 * @param rd
	 * @param rs
	 * @param rt
	 * @return
	 */
	public static LineOfCode createRTypeLineOfCode(String funct, Register rd, Register rs, Register rt, int lineNumber) {
		return new RLineOfCode(	InstructionWords.CMD_BIN_ALU,
				rd.getRegCode(),
				rs.getRegCode(),
				rt.getRegCode(),
				"00000",
				funct,
				lineNumber);
	}

	/**
	 * Crea una línea de código tipo J. RS o INMM deben ser nulos
	 * @param funct
	 * @param rs
	 * @param inmm
	 * @return
	 */
	public static LineOfCode createJTypeLineOfCode(String funct, Register rs, String inmm,int lineNumber) {
		return new JLineOfCode(	funct,
								rs.getRegCode(),
								inmm,
								lineNumber);
	}
}
