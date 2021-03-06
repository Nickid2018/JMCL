/*
 * Copyright 2021 Nickid2018
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.nickid2018.smcl.functions;

import java.util.function.*;
import com.github.nickid2018.smcl.*;
import com.github.nickid2018.smcl.set.*;
import com.github.nickid2018.smcl.util.*;

public abstract class FunctionBuilder<T extends FunctionStatement> {

	public static final DoubleConsumer ALL_DOMAIN = arg -> {
		;
	};

	public static final DoubleConsumer checkDomainExclude(DoublePredicate exclude, DoubleFunction<String> errorString) {
		return arg -> {
			if (exclude.test(arg))
				throw new ArithmeticException(errorString.apply(arg));
		};
	}

	public static final DoubleConsumer checkDomainInclude(DoublePredicate include, DoubleFunction<String> errorString) {
		return arg -> {
			if (!include.test(arg))
				throw new ArithmeticException(errorString.apply(arg));
		};
	}

	public static final DoubleConsumer checkDomainExclude(NumberSet set, DoubleFunction<String> errorString) {
		return arg -> {
			if (set.isBelongTo(arg))
				throw new ArithmeticException(errorString.apply(arg));
		};
	}

	public static final DoubleConsumer checkDomainInclude(NumberSet set, DoubleFunction<String> errorString) {
		return arg -> {
			if (!set.isBelongTo(arg))
				throw new ArithmeticException(errorString.apply(arg));
		};
	}

	public static final Double2DoubleFunction DEFAULT_RESULT = arg -> arg;

	public static final DoubleSMCLFunction DEFAULT_RESOLVE = (arg, smcl) -> arg;

	public static final DoubleSMCLFunction RESOLVE_RADIANS = (arg,
			smcl) -> smcl.settings.degreeAngle ? Math.toRadians(arg) : arg;

	public static final DoubleSMCLFunction RESOLVE_DEGREES = (arg,
			smcl) -> smcl.settings.degreeAngle ? Math.toDegrees(arg) : arg;

	protected final String name;

	public FunctionBuilder(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract T create(SMCL smcl, Statement... statements);
}
