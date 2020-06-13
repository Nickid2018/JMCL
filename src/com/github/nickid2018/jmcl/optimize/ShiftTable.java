package com.github.nickid2018.jmcl.optimize;

import java.util.*;
import com.github.nickid2018.jmcl.*;

public class ShiftTable {

	public Map<ShiftKey,String> table = new HashMap<>();
	public int length;
	public int[] shiftLevels;

	public void fill(String expr) throws MathException {
		table.clear();
		int len = length = expr.length();
		if (shiftLevels == null)
			shiftLevels = new int[len];
		if (shiftLevels.length < len)
			shiftLevels = new int[len];
		int shift = internalFill(expr, 0, 0);
		if (shift < len)
			throw new MathException("Parentheses are not paired: Excess right brackets", expr, shift);
	}

	private int internalFill(String expr, int shift, int level) throws MathException {
		ShiftKey key = new ShiftKey();
		String sign = "";
		key.start = shift;
		key.level = level;
		for (; shift < expr.length(); shift++) {
			shiftLevels[shift] = level;
			char now = expr.charAt(shift);
			if (now == '(') {
				shift = internalFill(expr, shift + 1, level + 1);
			}
			if (now == ')')
				break;

		}
		if (shift == expr.length() && level != 0)
			throw new MathException("Parentheses are not paired: Missing right brackets", expr, shift);
		key.end = shift;
		table.put(key, sign);
		return shift;
	}

	public int length() {
		return length;
	}
}
