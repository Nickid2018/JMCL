package com.github.nickid2018.jmcl.func;

import java.util.*;

import com.github.nickid2018.jmcl.*;

public class Sin extends FunctionStatement {

	@Override
	public double calc(Map<String, Double> values) {
		return Math.sin(ms.calc(values));
	}

	@Override
	public String toString() {
		if (!ms.getClass().equals(Variable.class))
			return "sin(" + ms + ")";
		else
			return "sin" + ms;
	}

	public static final Sin format(String s) throws MathException {
		Sin sin = new Sin();
		if (s.startsWith("sin")) {
			sin.ms = JMCLRegister.getStatement(s.substring(3));
		}
		return sin;
	}
}
