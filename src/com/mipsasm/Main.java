package com.mipsasm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.mipsasm.assembling.*;
import com.mipsasm.instructions.InstructionParser;
import com.mipsasm.util.ArgumentParser;
import com.mipsasm.util.debugging.Debugger;

public class Main {
	
	public static void main(String[] args) {
		String mainHelp = "Los parámetros para la ejecución son:";
		ArgumentParser argPar = new ArgumentParser(mainHelp);
		
		//Initialize variables and configure the argument parser
		argPar.addCommand(argPar.new ArgumentParserOption(
				"h", new String[]{"help"}, false, false, false, 	"Shows this help message", null, false));
		argPar.addCommand(argPar.new ArgumentParserOption(
				"f", new String[]{"file"}, true, true, true, 	"Ruta al fichero de origen", "path", false));
		argPar.addCommand(argPar.new ArgumentParserOption(
				"o", new String[]{"output"}, false, true, true, 	"Ruta al fichero de destino", "path", false));
		argPar.addCommand(argPar.new ArgumentParserOption (
				"d", new String[]{"debug"}, false, true, true, 		"Debug depth", "depth", false));
		argPar.addCommand(argPar.new ArgumentParserOption (
				"m", new String[]{"mips"}, false, false, false, "Si el código debe estar listo para mips no poner esto", null, false));
		argPar.addCommand(argPar.new ArgumentParserOption (
				"i", new String[]{"instructions"}, false, false, false, "Ayuda sobre las instrucciones", null, false));
		

		
		try {
			argPar.processArguments(args);
		} catch (IllegalArgumentException e) {
			Debugger.debug("Msg: " + e.getMessage(),0);
			System.out.println("Available commands: -h -f -o -d -m -i");
		}		
		
		if (argPar.hasCommandAppeared("h")) {
			System.out.println(argPar.getHelp());
		}
		if (argPar.hasCommandAppeared("i")) {
			new InstructionParser();
			System.out.println(InstructionParser.getInstructionHelp());
		}
		if (argPar.hasCommandAppeared("d")) {
			Debugger.setDebuggingDepth(Integer.parseInt(argPar.getArg("d")));
		}


		String inputCode;
		try {
			//"src/tp/pr3/cityLoader/cityFile";
			Debugger.debug("Reading file: " + argPar.getArg("f"), 0);
			inputCode = new Scanner(new File(argPar.getArg("f"))).useDelimiter("\\Z").next();
			Debugger.debug("File succesfully read", 0);
		} catch (Exception e) {
			Debugger.debug("Fatal read error: " + e.getMessage(), 0);
			return;
		}
		
		String resultado = "";
		try {
			resultado = Assembler.assemble(inputCode, argPar.hasCommandAppeared("m"));
		} catch (Exception e) {
			System.err.println("Fatal error while processing: " + e.getMessage());
			return;
		}
		System.out.println("\nAssembled code:");
		System.out.println(resultado);
		
		if (argPar.hasCommandAppeared("o")) {
			BufferedWriter writer = null;
			try {
			    //create a temporary file
				String file = argPar.getArg("o");
				File logFile = new File(file);
				
				// This will output the full path where the file will be written to...
			    System.out.println("Output file saved to: " + logFile.getCanonicalPath());
			
			    writer = new BufferedWriter(new FileWriter(logFile));
			    writer.write(resultado);
			} catch (Exception e) {
			    e.printStackTrace();
			} finally {
			    try {
			        writer.close();
			    } catch (Exception e) {}
			}
		}
		
	}
	
}
