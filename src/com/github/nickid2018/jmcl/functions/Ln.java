package com.github.nickid2018.jmcl.functions;

import com.github.nickid2018.jmcl.*;

public class Ln extends FunctionStatement {

	@Override
	public double calculate(VariableList list) {
		double v = ms.calculate(list);
		if (v <= 0)
			throw new ArithmeticException("ln:argument is invalid-" + v);
		return Math.log(v);
	}

	@Override
	public String toString() {
		if (!ms.getClass().equals(Variable.class))
			return "ln(" + ms + ")";
		else
			return "ln" + ms;
	}

	public static final Ln format(String s, JMCL jmcl) throws MathException {
		Ln ln = jmcl.obtain(Ln.class);
		if (s.startsWith("ln")) {
			ln.ms = JMCLRegister.getStatement(s.substring(2), jmcl);
		}
		return ln;
	}
}
