package com.github.nickid2018.jmcl.functions;

import com.github.nickid2018.jmcl.*;

public class Sin extends FunctionStatement {

	@Override
	public double calculate(VariableList list) {
		return JMCL.radium ? Math.sin(ms.calculate(list)) : Math.sin(Math.toRadians(ms.calculate(list)));
	}

	@Override
	public String toString() {
		if (!ms.getClass().equals(Variable.class))
			return "sin(" + ms + ")";
		else
			return "sin" + ms;
	}

	public static final Sin format(String s) throws MathException {
		Sin sin = JMCL.obtain(Sin.class);
		if (s.startsWith("sin")) {
			sin.ms = JMCLRegister.getStatement(s.substring(3));
		}
		return sin;
	}
}