package com.cj.jmcl.func;

import java.util.*;
import com.cj.jmcl.*;

public class Lg extends Function {
	
	@Override
	public double calc(Map<String, Double> values) {
		double v=ms.calc(values);
		if(v<=0)throw new ArithmeticException("lg:argument is invalid-"+v);
		return Math.log10(v);
	}
	
	@Override
	public String toString() {
		if(!ms.getClass().equals(Variable.class))
			return "lg("+ms+")";
		else return "lg"+ms;
	}

	public static final Lg format(String s) throws MathException {
		Lg lg=new Lg();
		if(s.startsWith("lg")) {
			lg.ms=JMCLRegister.getStatement(s.substring(2));
		}
		return lg;
	}
}
