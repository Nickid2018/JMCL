package com.github.nickid2018.jmcl;

import com.github.nickid2018.jmcl.statements.*;

public abstract class Statement implements AutoCloseable {

	protected int shares = 1;
	protected JMCL jmcl;

	public void share() {
		shares++;
	}

	public void unshare() {
		shares--;
	}

	@Override
	public final void close() {
		free();
	}

	public final boolean free() {
		shares--;
		if (shares < 1 && canClose()) {
			jmcl.free(this);
			doOnFree();
			return true;
		}
		return false;
	}

	public final boolean tryOnlyFree() {
		shares--;
		if (shares < 1 && canClose()) {
			jmcl.free(this);
			return true;
		}
		return false;
	}

	public void doOnFree() {
	}

	public boolean canClose() {
		return true;
	}

	@Override
	public abstract String toString();

	public abstract boolean isAllNum();

	public abstract double calculate(VariableList list);

	public abstract void setValues(Statement... statements);

	public static Statement format(String expr) throws MathException {
		return MathStatement.format(expr);
	}
}
