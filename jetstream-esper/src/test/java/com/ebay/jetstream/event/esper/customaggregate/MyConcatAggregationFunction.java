/*
Pulsar
Copyright (C) 2013-2015 eBay Software Foundation
Dual licensed under the Apache 2.0 license and the GPL v2 license.  See LICENSE for full terms.
*/
package com.ebay.jetstream.event.esper.customaggregate;

import com.espertech.esper.epl.agg.aggregator.AggregationMethod;

public class MyConcatAggregationFunction implements AggregationMethod {

	private static char DELIMITER = '1';
	private StringBuilder builder;
	private String delimiter;

	public MyConcatAggregationFunction() {
		builder = new StringBuilder();
		delimiter = "";
	}

	@Override
	public void enter(Object value) {
		if (value != null) {
			builder.append(delimiter);
			builder.append(value.toString());
			delimiter = Character.toString(DELIMITER);
		}
	}

	@Override
	public void leave(Object value) {
		if (value != null) {
			builder.setLength(0);
		}
	}

	@Override
	public Object getValue() {
		return builder.toString();
	}

	@Override
	public Class getValueType() {
		return String.class;
	}

	@Override
	public void clear() {
		builder = new StringBuilder();
		delimiter = "";
	}

}
