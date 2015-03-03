package com.mipsasm.instructions;


import java.util.Iterator;
import java.util.Vector;

import com.mipsasm.assembling.input.Code;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.loc.LineOfCode;
import com.mipsasm.instructions.binary.iType.LuiInstruction;
import com.mipsasm.instructions.binary.iType.branch.BalInstruction;
import com.mipsasm.instructions.binary.iType.branch.BeqInstruction;
import com.mipsasm.instructions.binary.iType.branch.BgeInstruction;
import com.mipsasm.instructions.binary.iType.branch.BgtInstruction;
import com.mipsasm.instructions.binary.iType.branch.BleInstruction;
import com.mipsasm.instructions.binary.iType.branch.BltInstruction;
import com.mipsasm.instructions.binary.iType.branch.BneInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.AddiInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.AndiInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.InInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.LwInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.OriInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.OutInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.SllInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.SraInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.SrlInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.SwInstruction;
import com.mipsasm.instructions.binary.iType.nonbranch.XoriInstruction;
import com.mipsasm.instructions.binary.jType.JalrInstruction;
import com.mipsasm.instructions.binary.jType.JrInstruction;
import com.mipsasm.instructions.binary.pseudoType.JInstruction;
import com.mipsasm.instructions.binary.pseudoType.LiInstruction;
import com.mipsasm.instructions.binary.pseudoType.MovInstruction;
import com.mipsasm.instructions.binary.pseudoType.NopInstruction;
import com.mipsasm.instructions.binary.rType.AddInstruction;
import com.mipsasm.instructions.binary.rType.AndInstruction;
import com.mipsasm.instructions.binary.rType.MultInstruction;
import com.mipsasm.instructions.binary.rType.NorInstruction;
import com.mipsasm.instructions.binary.rType.OrInstruction;
import com.mipsasm.instructions.binary.rType.SllvInstruction;
import com.mipsasm.instructions.binary.rType.SravInstruction;
import com.mipsasm.instructions.binary.rType.SrlvInstruction;
import com.mipsasm.instructions.binary.rType.SubInstruction;
import com.mipsasm.instructions.binary.rType.XorInstruction;
import com.mipsasm.instructions.exceptions.InvalidInstructionException;
import com.mipsasm.instructions.exceptions.InvalidRegisterException;
import com.mipsasm.instructions.exceptions.UnsupportedTagException;
import com.mipsasm.instructions.nonBinary.TagInstruction;
import com.mipsasm.util.debugging.Debugger;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;

public class InstructionParser {
	private static Vector<Instruction> instrucciones = new Vector<Instruction>();
	static {
		Debugger.debug("Initializing Instructions", 1);
		//instrucciones.add();
		//NO binarias
		instrucciones.add(new TagInstruction());
		//binarias
		instrucciones.add(new AddInstruction());
		instrucciones.add(new AndInstruction());
		instrucciones.add(new MultInstruction());
		instrucciones.add(new NorInstruction());
		instrucciones.add(new OrInstruction());
		instrucciones.add(new SllvInstruction());
		instrucciones.add(new SravInstruction());
		instrucciones.add(new SrlvInstruction());
		instrucciones.add(new SubInstruction());
		instrucciones.add(new XorInstruction());
		instrucciones.add(new SraInstruction());		
		instrucciones.add(new SrlInstruction());		
		instrucciones.add(new SllInstruction());		
		instrucciones.add(new AddiInstruction());
		instrucciones.add(new AndiInstruction());
		instrucciones.add(new BeqInstruction());
		instrucciones.add(new BgeInstruction());
		instrucciones.add(new BgtInstruction());
		instrucciones.add(new BleInstruction());
		instrucciones.add(new BltInstruction());
		instrucciones.add(new BneInstruction());
		instrucciones.add(new BalInstruction());
		instrucciones.add(new LuiInstruction());
		instrucciones.add(new OriInstruction());
		instrucciones.add(new XoriInstruction());
		instrucciones.add(new LiInstruction());
		instrucciones.add(new JrInstruction());
		instrucciones.add(new JInstruction());
		instrucciones.add(new JalrInstruction());
		instrucciones.add(new MovInstruction());
		instrucciones.add(new SwInstruction());
		instrucciones.add(new LwInstruction());
		instrucciones.add(new NopInstruction());
		instrucciones.add(new InInstruction());
		instrucciones.add(new OutInstruction());
	}
	
	public LineOfCode parseInstruction(Code code) throws InvalidInstructionException, InvalidRegisterException, OutOfBinaryRangeException, EndOfCodeException, UnsupportedTagException {
		for (int i = 0; i < InstructionParser.instrucciones.size(); i++) {
			try {
				return InstructionParser.instrucciones.get(i).parse(code);
			} catch (InvalidInstructionException e) {
				//ignoramos esta, el resto no pues si se dan quiere decir que teníamos una instrucci�n válida con parámetros erróneos
				Debugger.debug(e.getMessage(),3);
			}
		}
		throw new InvalidInstructionException("Instrucción inexistente");
	}
	
	public static String getInstructionHelp() {
		String ret = "";
		Iterator<Instruction> it = InstructionParser.instrucciones.iterator();
		while (it.hasNext()) {
			ret += it.next().getHelp() + "\n";
		}
		return ret;
	}
}
