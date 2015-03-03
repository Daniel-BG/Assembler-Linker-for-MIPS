package com.mipsasm.util.debugging;

public class Debugger {
	private static int depth = -1;
	/**
	 * Muestra el mensaje "message" si la profundidad es menor o igual a la actual
	 * @param message
	 * @param depth
	 */
	public static void debug (String message, int depth) {
		if (depth <= Debugger.depth)
			System.out.println("DBG[" + Integer.toString(depth) + "]:" + message);
	}
	/**
	 * Ajusta la profundidad del debug. Valor negativo omitirá mensajes
	 * @param depth
	 */
	public static void setDebuggingDepth(int depth) {
		if (depth < 0)
			throw new IllegalArgumentException("Debugger depth must be positive");
		Debugger.depth = depth;
	}
}
