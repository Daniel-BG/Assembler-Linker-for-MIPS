package com.mipsasm.assembling.input;

import java.util.HashMap;

import com.mipsasm.assembling.input.exceptions.DuplicatedTagException;
import com.mipsasm.assembling.input.exceptions.EndOfCodeException;
import com.mipsasm.assembling.input.exceptions.UnknownTagException;

import com.mipsasm.util.BinaryUtilities;
import com.mipsasm.util.debugging.*;
import com.mipsasm.util.exceptions.OutOfBinaryRangeException;


public class Code {
	private String code;
	private int codePointer = 0;
	private int lastCodePointerPosition = 0;
	private int currentLine = 0;
	private char[] endOfLineChar = {'\n','\r'};
	private char[] toIgnore = {' ',',',';','{','}','\t'};
	private char[] commentChar = {'#'};
	
	private HashMap<String, Integer> tags = new HashMap<String, Integer>();
	private String lastTag;
	private int lastTagLine = -1;
	
	private char lastRead; //último caracter leído
	private String lastTokenRead; //último string leído
	private String lastCommandRead = null;
	
	
	
	public Code(String code) {
		this.code = code;
	}
	
	////SETTERS
	public void setIgnore(char[] toIgnore) {
		this.toIgnore = toIgnore;
	}
	
	public void setCommentChar(char[] comment) {
		this.commentChar = comment;
	}
	/**
	 * Añade una etiqueta en la línea actual
	 * @param tag
	 * @throws DuplicatedTagException 
	 */
	public void setTagAtCurrentLine(String tag) throws DuplicatedTagException {
		if (this.tags.containsKey(tag)) {
			throw new DuplicatedTagException("Tag: " + tag + " found twice");
		}
		this.tags.put(tag, this.currentLine);
		this.lastTag = tag;
		this.lastTagLine = this.currentLine;
		Debugger.debug("New tag [" + tag + "] set at line: " + Integer.toString(this.currentLine), 2);
	}
	/**
	 * Pone un punto de recuperación de la lectura
	 */
	public void setRecoveryPoint() {
		this.lastCodePointerPosition = this.codePointer;
	}
	/**
	 * Recupera la lectura desde el último punto de lectura puesto, o desde el principio si nunca se ha puesto ninguno
	 */
	public void recoverLast() {
		this.codePointer = this.lastCodePointerPosition;
	}
	////
	
	////GETTERS
	public String getLastToken() {
		return this.lastTokenRead;
	}
	public int getCurrentLine() {
		return this.currentLine;
	}
	public String getAndResetCommand() {
		String temp = this.lastCommandRead;
		this.lastCommandRead = null;
		return temp;
	}
	public String getCurrentLineTag() {
		if (this.lastTagLine == this.currentLine)
			return this.lastTag;
		return null;
	}
	////
	
	/**
	 * Devuelve la línea donde se encuentra la etiqueta indicada
	 * @param tag
	 * @return
	 * @throws UnknownTagException
	 */
	public int getLineOfTag(String tag) throws UnknownTagException {
		Integer ret = this.tags.get(tag);
		if (ret == null)
			throw new UnknownTagException();
		return ret;
	}
	/**
	 * Incrementa la línea de código actual (instrucción en este caso)
	 */
	public void incrementLine() {
		this.currentLine++;
	}
	/**
	 * Incrementa o no el puntero de caracter actual
	 * @param backwards
	 */
	private void incrementChar(boolean backwards) {
		this.codePointer += backwards ? -1:1;
	}
	/**
	 * Actualiza el comando actual con el nuevo token
	 */
	private void updateCommand() {
		this.lastCommandRead = this.lastCommandRead == null ? 
				this.lastTokenRead : 
				this.lastCommandRead + " " + this.lastTokenRead;
	}
	/**
	 * Lee y devuelve la siguiente palabra en el código, ignorando comentarios y caracteres separadores
	 * @return
	 */
	public String readToken() throws EndOfCodeException {
		String ret = this.nextToken();
		this.updateCommand();
		return ret;
	}
	/**
	 * Gets the next token in the code
	 * @return
	 * @throws EndOfCodeException
	 */
	private String nextToken() throws EndOfCodeException {
		String ret = "";
		do {
			try {
				while (readNextChar()) {
					ret += this.lastRead;
				}
			} catch (EndOfCodeException e) {
				if (ret.equals(""))
					throw e;
			}
		} while (ret.equals(""));
		this.lastTokenRead = ret;
		return ret;
	}
	/**
	 * Comprueba si la siguiente palabra es igual a la enviada. Si lo es, sitúa el puntero de lectura en la siguiente
	 * y devuelve verdadero, si no, devuelve falso y deja el puntero donde estaba antes.
	 * @param str
	 * @return
	 */
	public boolean checkToken(String str) throws EndOfCodeException{
		this.setRecoveryPoint();
		String token = this.nextToken();
		if (token.equals(str)) {
			this.updateCommand();
			return true;
		}
		this.recoverLast();
		return false;
	}
	/**
	 * Devuelve la siguiente palabra manteniendo el puntero igual
	 * @return
	 * @throws EndOfCodeException
	 */
	public String getToken() throws EndOfCodeException {
		this.setRecoveryPoint();
		String token = this.nextToken();
		this.recoverLast();
		return token;
	}
	/**
	 * Lee el siguiente caracter. Devuelve verdadero si el primero que leyó era correcto, 
	 * falso si no, y lo dejará preparado con el puntero en el siguiente correcto
	 * @return
	 */
	private boolean readNextChar() throws EndOfCodeException {
		//ignoramos todo lo ignorable
		int beginElim = this.codePointer;
		do {
			do {
				Code.checkOutOfBounds(this); //si hemos acabado saldremos
				this.lastRead = this.code.charAt(this.codePointer);
				this.incrementChar(false);
			} while (Code.charIsInList(this.lastRead, this.toIgnore) || Code.charIsInList(this.lastRead,this.endOfLineChar));
			
			//ignoramos caracter comentario y lo que venga después
			if (Code.charIsInList(this.lastRead, commentChar)) {
				while (!Code.charIsInList(this.code.charAt(this.codePointer),this.endOfLineChar)) {
					this.incrementChar(false);
					Code.checkOutOfBounds(this); //nuevo check por si nos salimos aquí
				}
				this.incrementChar(false);
			}
		} while (Code.charIsInList(this.lastRead, this.toIgnore) || Code.charIsInList(this.lastRead, this.commentChar));
		
		//si habíamos ignorado cosas, es que habíamos terminado la palabra, así que devovlemos false 
		//tras restaurar el codePointer para apuntar a LastRead
		if (this.codePointer > beginElim + 1) {
			this.incrementChar(true);
			return false;
		} else { //si no habíamos ignorado cosas, estábamos leyendo
			return true;
		}
	}
	
	
	/**
	 * Devuelve el valor del número o tag contenido
	 * @param str
	 * @return
	 * @throws OutOfBinaryRangeException 
	 */
	public static String getValueOrTag(String str, int length, Code code, int callerLine) throws OutOfBinaryRangeException {
		try {
			//esto es o que devuelve si saltamos a un número
			return BinaryUtilities.toBinStringSigned(str, length);
		} catch (NumberFormatException e) {
			//y este es el código que detecta si saltamos a una etiqueta o a otro lado
			try {
				String a = BinaryUtilities.toBinStringSigned(code.getLineOfTag(str) - callerLine, length);
				Debugger.debug("Linked tag: '" + str + "' linking to [" + code.getLineOfTag(str) + "] from [" + callerLine +"]", 2);
				return a;
			} catch (UnknownTagException e1) {
				throw new IllegalArgumentException("Etiqueta: [" + str + "] desconocida" );
			}
		} 
	
	}

	////FUNCIONES EST�TICAS
	/**
	 * Comprueba si un código ha terminado de leerse
	 * @param code
	 * @return
	 */
	private static boolean isOutOfBounds(Code code) {
		if (code.codePointer >= code.code.length())
			return true;
		return false;
	}
	/**
	 * Mira si se ha salido del c�digo la lectura y de ser as� lanza una excepci�n
	 * @param code
	 * @throws EndOfCodeException
	 */
	private static void checkOutOfBounds(Code code) throws EndOfCodeException{
		if (Code.isOutOfBounds(code))
			throw new EndOfCodeException("End of code reached");
	}
	/**
	 * Comprueba si un caracter est� en un array de caracteres
	 * @param a
	 * @param list
	 * @return
	 */
	private static boolean charIsInList(char a, char[] list) {
		if (list == null)
			return false;
		for (int i = 0; i < list.length; i++)
			if (a == list[i])
				return true;
		return false;
	}
	////

	
}
