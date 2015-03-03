package com.mipsasm.instructions.binary;
/*
public static final String CMD_
public static final String CMD_BIN_
 */
public class InstructionWords {
	//INSTRUCCIONES
	//beq 000100
	public static final String CMD_BEQ = "beq";
	public static final String CMD_BIN_BEQ = "000100";
	//bne 000101
	public static final String CMD_BNE = "bne";
	public static final String CMD_BIN_BNE = "000101";
	//ble 000110
	public static final String CMD_BLE = "ble";
	public static final String CMD_BIN_BLE = "000110";
	//bgt 000111
	public static final String CMD_BGT = "bgt";
	public static final String CMD_BIN_BGT = "000111";
	//sll 000000
	public static final String CMD_SLL = "sll";
	public static final String CMD_BIN_SLL = "001001";
	//srl 000010
	public static final String CMD_SRL = "srl";
	public static final String CMD_BIN_SRL = "001010";
	//sra 000011
	public static final String CMD_SRA = "sra";
	public static final String CMD_BIN_SRA = "001011";
	//addi 001000
	public static final String CMD_ADDI = "addi";
	public static final String CMD_BIN_ADDI = "001000";
	//andi 001100
	public static final String CMD_ANDI = "andi";
	public static final String CMD_BIN_ANDI = "001100";
	//ori 001101
	public static final String CMD_ORI = "ori";
	public static final String CMD_BIN_ORI = "001101";
	//xori 001110
	public static final String CMD_XORI = "xori";
	public static final String CMD_BIN_XORI = "001110";
	//lui 001111
	public static final String CMD_LUI = "lui";
	public static final String CMD_BIN_LUI = "001111";
	//bal 010101
	public static final String CMD_BAL = "bal";
	public static final String CMD_BIN_BAL = "010101";
	//blt 010110
	public static final String CMD_BLT = "blt";
	public static final String CMD_BIN_BLT = "010110";
	//bge 010111
	public static final String CMD_BGE = "bge";
	public static final String CMD_BIN_BGE = "010111";
	//lw 100011
	public static final String CMD_LW = "lw";
	public static final String CMD_BIN_LW = "100011";
	//sw 101011
	public static final String CMD_SW = "sw";
	public static final String CMD_BIN_SW = "101011";
	//in 111111
	public static final String CMD_IN = "in";
	public static final String CMD_BIN_IN = "111111";
	//out 111110
	public static final String CMD_OUT = "out";
	public static final String CMD_BIN_OUT = "111110";
	//Operación de ALU 000000
	public static final String CMD_ALU = null;
	public static final String CMD_BIN_ALU = "000000";
	
	//FUNCIONES ALU

	//sllv 000100
	public static final String CMD_SLLV = "sllv";
	public static final String CMD_BIN_SLLV = "000100";
	//srlv 000110
	public static final String CMD_SRLV = "srlv";
	public static final String CMD_BIN_SRLV = "000110";
	//srav 000111
	public static final String CMD_SRAV = "srav";
	public static final String CMD_BIN_SRAV = "000111";
	//jr 001000
	public static final String CMD_JR = "jr";
	public static final String CMD_BIN_JR = "010000";
	//jalr 001001
	public static final String CMD_JALR = "jalr";
	public static final String CMD_BIN_JALR = "010001";
	//mult 011000
	public static final String CMD_MULT = "mult";
	public static final String CMD_BIN_MULT = "011000";
	//add 100000
	public static final String CMD_ADD = "add";
	public static final String CMD_BIN_ADD = "100000";
	//sub 100010
	public static final String CMD_SUB = "sub";
	public static final String CMD_BIN_SUB = "100010";
	//and 100100
	public static final String CMD_AND = "and";
	public static final String CMD_BIN_AND = "100100";
	//or 100101
	public static final String CMD_OR = "or";
	public static final String CMD_BIN_OR = "100101";
	//xor 100110
	public static final String CMD_XOR = "xor";
	public static final String CMD_BIN_XOR = "100110";
	//nor 100111
	public static final String CMD_NOR = "nor";
	public static final String CMD_BIN_NOR = "100111";
	
	//PseudoInstrucciones
	public static final String CMD_LI = "li";
	public static final String CMD_BIN_PSEUDO = "0";
	public static final String CMD_J = "j";
	public static final String CMD_MOV = "mov";
	public static final String CMD_NOP = "nop";
}
