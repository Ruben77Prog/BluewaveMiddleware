package com.ruben.bluewave.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractValueObject {

	public AbstractValueObject() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
