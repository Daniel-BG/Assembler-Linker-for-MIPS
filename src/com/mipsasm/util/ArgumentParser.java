package com.mipsasm.util;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class ArgumentParser {
	private String mainHelp;
	private ArrayList<ArgumentParserOption> options = new ArrayList<ArgumentParserOption>();
	private boolean isProcessing = false;
	
	
	//CONSTUCTORS AND SETTERS
	
	/**
	 * Creates the argument parser
	 * @param mainHelp Introduction to the generated help that comes afterwards.
	 */
	public ArgumentParser(String mainHelp) {
		this.mainHelp = mainHelp;
	}
	
	/**
	 * Adds a new command to the parser, so it can be identified when parsing. <br> 
	 * If a command is repeated, this doesn't fail, but only the first one will be tested for matches.
	 * @param command New command (ArgumentParserOption class) containing all the necessary information for the parser)
	 */
	public void addCommand(ArgumentParserOption command) {
		if (isProcessing)
			throw new ConcurrentModificationException("Can't append a new Option while processing arguments");
		this.options.add(command);
	}
	
	
	//PROCESSING
	
	/**
	 * Process a chain of arguments
	 * @param args
	 * @throws IllegalArgumentException If the arguments don't meet the specification set on the constructor
	 */
	public void processArguments(String[] args) throws IllegalArgumentException {
		this.isProcessing = true;
		this.doProcessArguments(args);
		this.isProcessing = false;
	}
	/**
	 * Does the actual processing
	 * @param args
	 * @throws IllegalArgumentException If the arguments don't meet the specification set on the constructor
	 */
	private void doProcessArguments(String[] args) throws IllegalArgumentException{
		for (int i = 0; i < args.length; i++) {
			int arg;
			try {
				arg = this.checkForArgument(this.toCommand(args[i],1));
			} catch (IllegalArgumentException e) {
				arg = this.checkForWord(this.toCommand(args[i],2));
			}
			this.options.get(arg).hasAppeared = true;
			if (this.options.get(arg).isArgMandatory) {
				i++;
				try {
					this.options.get(arg).argument = args[i];
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new IllegalArgumentException("A mandatory argument did not appear: " + this.options.get(arg).command);
				}
			}
			else if (this.options.get(arg).hasArg && i < args.length-1)
				try {
					i++;
					this.options.get(arg).argument = this.toArg(args[i]);
				} catch (IllegalArgumentException e) {
					i--;
				} 
		}
		
		for (int i = 0; i < this.options.size(); i++)
			if (this.options.get(i).isCmdMandatory && !this.options.get(i).hasAppeared)
				throw new IllegalArgumentException("A mandatory command did not appear: " + this.options.get(i).command);
	}
	/**
	 * Checks for the existance of a command in the command array sent on the constructor
	 * @param command
	 * @return the position that command occupies on the array
	 * @throws IllegalArgumentException when the command does not exist
	 */
	private int checkForArgument(String command) throws IllegalArgumentException {
		for (int i = 0; i < this.options.size(); i++)
			if (this.options.get(i).command.equals(command))
				return i;
		throw new IllegalArgumentException("Argument Unknown");
	}
	/**
	 * Checks for the existance of a command in the command array sent on the constructor
	 * @param command
	 * @return the position that command occupies on the array
	 * @throws IllegalArgumentException when the command does not exist
	 */
	private int checkForWord(String command) throws IllegalArgumentException {
		for (int i = 0; i < this.options.size(); i++)
			if (this.options.get(i).commandWords != null)
				for (int j = 0; j < this.options.get(i).commandWords.length; j++)
					if (this.options.get(i).commandWords[j].equals(command))
						return i;
		throw new IllegalArgumentException("Argument Unknown: " + command);
	}
	/**
	 * Checks if a String meets the specifications of being a command, and if so, returns it properly shaped (without the '-')
	 * @param s
	 * @return
	 * @throws IllegalArgumentException If the string is null or its lenght is < 2, or if it doesn't start with '-'
	 */
	private String toCommand(String s, int numberOfLines)  throws IllegalArgumentException{
		if (s == null || s.length() < 2)
			throw new IllegalArgumentException("Null or not long enough argument sent");
		if (s.charAt(0) != '-')
			throw new IllegalArgumentException("Arguments must start with '-': " + s);
		return this.forceRemoveLine(s, numberOfLines);
	}
	/**
	 * Removes the character '-', if present, from the beggining of s, and all subsequent '-' characters from the beginning
	 * @param s
	 * @param numberOfLines number of '-' to remove
	 * @return the new String with the removed character(s)
	 * @throws IllegalArgumentException when s is null or its lenght is zero
	 */
	private String removeLine(String s, int numberOfLines) throws IllegalArgumentException{
		return doRemoveLine(s,numberOfLines,false);
	}
	/**
	 * Removes the character '-', from the beggining of s, and all subsequent '-' characters from the beginning
	 * @param s
	 * @param numberOfLines number of '-' to remove
	 * @return the new String with the removed character(s)
	 * @throws IllegalArgumentException when removeLine would do so or there is no '-' at the beggining the number of times specified by numberOfLines
	 */
	private String forceRemoveLine(String s, int numberOfLines) throws IllegalArgumentException {
		return doRemoveLine(s,numberOfLines,true);
	}
	/**
	 * Does the actual removal of the '-' character
	 * @param s
	 * @param numberOfLines number of times it needs to be removed
	 * @param throwWhenMissing if it should throw an exception when there are not enough instances of the character
	 * @return the new string
	 * @throws IllegalArgumentException when the string is null, too short, or it has not enough '-' characters when throwWhenMissing flag is true
	 */
	private String doRemoveLine(String s, int numberOfLines, boolean throwWhenMissing) throws IllegalArgumentException{
		if (s == null)
			throw new IllegalArgumentException("The argument can't be null!");
		String ret = s;
		for (int i = 0; i < numberOfLines; i++){
			if (ret.length() == 0)
				throw new IllegalArgumentException("Not long enough: " + s);
			if (ret.charAt(0) == '-')
				ret = ret.substring(1);
			else if (throwWhenMissing)
				throw new IllegalArgumentException("- missing from argument: " + ret);
		}
		if (ret == "")
			throw new IllegalArgumentException("A String with only \"-\" is not accepted");
		if (ret.charAt(0) == '-')
			throw new IllegalArgumentException("More '-' found than needed");
		
		return ret;
	}
	/**
	 * Checks if a String is an argument and not a command
	 * @param s 
	 * @return the string if it was indeed an argument
	 * @throws IllegalArgumentException when it was a command (starts with '-') or null, instead of an argument
	 */
	private String toArg(String s) throws IllegalArgumentException {
		if (s == null || s.length() == 0 || s.charAt(0) == '-')
			throw new IllegalArgumentException("Illegal argument after command");
		return s;
	}
	
	
	//GETTERS AND OTHER PUBLIC STUFF
	
	/**
	 * Gets the associated argument to the specified command
	 * @param command
	 * @return The received argument, or NULL if there was no argument received (only for non mandatory argument commands)
	 */
	public String getArg(String command) {
		int res = this.checkForArgument(this.removeLine(command,1));
		return this.options.get(res).argument;
	}
	/**
	 * Checks if a command has appeared when parsing the arguments. Note that if this method is called on a mandatory command, it will always return true after processing, since it not appearing means that an exception is thrown before
	 * @param command
	 * @return true if it has appeared, false otherwise
	 * @throws IllegalArgumentException when the command does not exist within this object's possible commands list sent in the constructor
	 */
	public boolean hasCommandAppeared(String command) throws IllegalArgumentException{
		return this.options.get(this.checkForArgument(this.removeLine(command,1))).hasAppeared;
	}
	/**
	 * Gets the help for the arguments this object parses
	 * @return The help in this format:
	 * -arg
	 * [is mandatory]
	 * [has mandatory argument] | [has optional argument] | [has no argument]
	 * [help set on the constructor, if exists]
	 */
	public String getHelp() {
		String help = (this.mainHelp != null ? this.mainHelp : "");
		for (int i = 0; i < this.options.size(); i++)
			help += this.options.get(i).isHidden ? "" : " " + this.options.get(i).getUsage();
		

		int max = 0;
		for (int i = 0; i < this.options.size(); i++)
			max = this.options.get(i).getBasicHelp().length() > max ? this.options.get(i).getBasicHelp().length() : max;

		for (int i = 0; i < this.options.size(); i++)
			help += (!this.options.get(i).isHidden) ? ("\n" + ArgumentParser.setSpaces(this.options.get(i).getBasicHelp(), max) + this.options.get(i).help) : "";
						
		return help == "" ? "No help available" : help;	
	}
	
	
	//STATIC FUNCTIONS
	
	/**
	 * Appends spaces to make the string match max length. Adds another 3 afterwards
	 * @param string
	 * @param max
	 * @return The new string
	 */
	private static String setSpaces(String string, int max) {
		int actual = string.length();
		String append = "";
		for (int i = 0; i < max-actual; i++)
			append += " ";
		append += "   ";
		
		return string + append;
	}

	
	//INNER CLASSES
	
	/**
	 * Class used to configure the argument parser
	 */
	public class ArgumentParserOption {
		private String command;
		private String[] commandWords;
		private boolean isCmdMandatory;
		private boolean hasArg;
		private boolean isArgMandatory;
		private boolean hasAppeared = false;
		private String help;
		private String argument;
		private String argumentName;
		private boolean isHidden;
		
		/**
		 * Builds the option. Will throw exceptions if: <br>
		 *  -command is null or !hasArg && isArgMandatory <br>
		 *  -commandWords != null && any of its components is null.
		 * @param command basic command used to identify a command. Can be sent with or without the beginning '-'
		 * @param commandWords additional full words for the same purpose. Can be null. Can be sent with or without the beginning '-'
		 * @param isCmdMandatory if the command has to appear when parsing
		 * @param hasArg if an argument is to be expected after the command
		 * @param isArgMandatory if said argument is mandatory
		 * @param help additional help to show after the auto-generated one. Can be null
		 * @param argumentName name of the argument (to be shown when getting help). Can be null
		 * @param isHidden if the option is hidden and should not be shown when asking for help (as in ArgumentParser.getHelp())
		 * @throws IllegalArgumentException when the arguments are not correct
		 */
		public ArgumentParserOption(String command, String[] commandWords, boolean isCmdMandatory, boolean hasArg, boolean isArgMandatory, String help, String argumentName, boolean isHidden) throws IllegalArgumentException {
			if (command == null || (!hasArg && isArgMandatory))
				throw new IllegalArgumentException("Invalid Parameters");
			if (commandWords != null) {
				for (int i = 0; i < commandWords.length; i++)
					if (commandWords[i] == null)
						throw new IllegalArgumentException("You can't send a null value inside the commandWords array");
					else
						commandWords[i] = removeLine(commandWords[i],2);
			}
			
			this.command = removeLine(command,1);
			this.commandWords = commandWords;
			this.isCmdMandatory = isCmdMandatory;
			this.hasArg = hasArg;
			this.isArgMandatory = isArgMandatory;
			this.help = help;
			this.argumentName = argumentName == null ? "arg" : argumentName;
			this.isHidden = isHidden;
		}
		
		/**
		 * Gets the usage for the option. The format is [-<command> [(,--<commandWord[i]>) <<argumentName>>]]
		 * @return A string with that format.
		 */
		public String getUsage() {
			String usage = "[-" + this.command;
			if (this.hasArg)
				usage += " <" + this.argumentName + ">";
			return usage + "]";
		}
		
		/**
		 * Gets the basic help of the option, only with auto-generated content
		 * @return
		 */
		public String getBasicHelp() {
			String help = " -" + this.command;
			if (commandWords != null) {
				for (int i = 0; i < this.commandWords.length; i++)
					help += ",--" + this.commandWords[i];
				if (this.hasArg)
					help += " <" + this.argumentName + ">";
			}
			return help;
		}
	
		/**
		 * Gets the additional help sent in the constructor. Can be null if it was sent that way.
		 * @return
		 */
		public String getAdditionalHelp() {
			return this.help;
		}
		
		/**
		 * Returns the basic and additional help together and properly formatted
		 * @return
		 */
		public String getHelp() {
			return this.help + "   " + this.getAdditionalHelp();
		}	
	}
}



//for (int i = 0; i < this.options.size(); i++) {
//help += (this.options.get(i).help != null ? ("\n" + this.options.get(i).help) : "");
//	help += "-" + this.commands[i] + ":\n";
//	if (this.isCmdMandatory[i])
//		help += "\tMandatory command\n";
//	else
//		help += "\tOptional command\n";
//	if (this.hasArg[i] && this.isCmdMandatory[i])
//		help += "\tHas a mandatory argument\n";
//	else if (this.hasArg[i])
//		help += "\tHas an optional argument\n";
//	else 
//		help += "\tHas no argument\n";
//	if (this.help[i] != null)
//		help += "\t" + this.help[i] + "\n";
//}
