package com.mipsasm.instructions;


import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.util.debugging.Debugger;

public class Register { 
	private static Vector<Register> registros = new Vector<Register>();
	private static RegisterComparator regComp = new RegisterComparator();
	
	
	static {
		Debugger.debug("Initializing Registers", 1);
		registros.add(new Register("zero","00000"));
		registros.add(new Register("temp","00001"));
		registros.add(new Register("v0","00010"));
		registros.add(new Register("v1","00011"));
		registros.add(new Register("a0","00100"));
		registros.add(new Register("a1","00101"));
		registros.add(new Register("a2","00110"));
		registros.add(new Register("a3","00111"));
		registros.add(new Register("s0","01000"));
		registros.add(new Register("s1","01001"));
		registros.add(new Register("s2","01010"));
		registros.add(new Register("s3","01011"));
		registros.add(new Register("s4","01100"));
		registros.add(new Register("s5","01101"));
		registros.add(new Register("s6","01110"));
		registros.add(new Register("s7","01111"));
		registros.add(new Register("t0","10000"));
		registros.add(new Register("t1","10001"));
		registros.add(new Register("t2","10010"));
		registros.add(new Register("t3","10011"));
		registros.add(new Register("t4","10100"));
		registros.add(new Register("t5","10101"));
		registros.add(new Register("t6","10110"));
		registros.add(new Register("t7","10111"));
		registros.add(new Register("t8","11000"));
		registros.add(new Register("t9","11001"));
		registros.add(new Register("t10","11010"));
		registros.add(new Register("t11","11011"));
		registros.add(new Register("t12","11100"));
		registros.add(new Register("t13","11101"));
		registros.add(new Register("sp","11110"));
		registros.add(new Register("ra","11111"));
		registros.add(new Register("r0","00000"));
		registros.add(new Register("r1","00001"));
		registros.add(new Register("r2","00010"));
		registros.add(new Register("r3","00011"));
		registros.add(new Register("r4","00100"));
		registros.add(new Register("r5","00101"));
		registros.add(new Register("r6","00110"));
		registros.add(new Register("r7","00111"));
		registros.add(new Register("r8","01000"));
		registros.add(new Register("r9","01001"));
		registros.add(new Register("r10","01010"));
		registros.add(new Register("r11","01011"));
		registros.add(new Register("r12","01100"));
		registros.add(new Register("r13","01101"));
		registros.add(new Register("r14","01110"));
		registros.add(new Register("r15","01111"));
		registros.add(new Register("r16","10000"));
		registros.add(new Register("r17","10001"));
		registros.add(new Register("r18","10010"));
		registros.add(new Register("r19","10011"));
		registros.add(new Register("r20","10100"));
		registros.add(new Register("r21","10101"));
		registros.add(new Register("r22","10110"));
		registros.add(new Register("r23","10111"));
		registros.add(new Register("r24","11000"));
		registros.add(new Register("r25","11001"));
		registros.add(new Register("r26","11010"));
		registros.add(new Register("r27","11011"));
		registros.add(new Register("r28","11100"));
		registros.add(new Register("r29","11101"));
		registros.add(new Register("r30","11110"));
		registros.add(new Register("r31","11111"));
		registros.add(new Register("null",null));
		Collections.sort(registros, regComp);
	}
	
	private final String name;		//nombre del registro 
	private final String regCode;	//código en binario del registro
	
	private Register (String name, String regcode) {
		this.name = name;
		this.regCode = regcode;
	}
	
	//GETTERS
	public String getRegCode() {
		return this.regCode;
	}
	public String getName() {
		return this.name;
	}
	//función externa para buscar un registro partiendo de un nombre
	public static Register getReg(String name) throws InvalidRegisterException {
		int res = Collections.binarySearch(registros, new Register(name, null), regComp);
		if (res < 0)
			throw new InvalidRegisterException("Nombre de registro no válido");
		return registros.get(res);
	}
	
	/**
	 * Clase interna para comparar registros unos con otros y poder hacer eficientemente las búsquedas de registro
	 */
	private static class RegisterComparator implements Comparator<Register> {

		@Override
		public int compare(Register arg0, Register arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
		
	}
}
