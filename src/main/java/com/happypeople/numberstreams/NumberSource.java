package com.happypeople.numberstreams;

import java.io.Serializable;

/** A stream of numbers, endless by definition.
 * For simplification, we use the java.io serialization feature.
 * Connections between NumberSources (the inputs) are not covered by the serialization, that part
 * is covered by the "magic" class DynamicSubGraph.
 * Further on, NumberSource object are immutable value objects. Do not implement setters!
 * see Strategy pattern
 */
public interface NumberSource extends Serializable {
	/** Read numbers
	 * @param buffer is filled with numbers by the call to this method.
	 * @return the count of numbers actually written to buffer. Like InputStream.read(buf)
	 */
	public int read(double[] buffer);
	
	/** Setting the source for the input of this NumberSource.
	 * Some NumberSource may not use any input, some exactly one input (or another number), some
	 * a dynamic number.
	 * Note that the inputs are transient by definition, they are not serialized.
	 * They are used as is in the implementations of read(...).
	 * @param inputSource the source to use as input
	 * @param idx the index of the "input channel", starting at 0
	 */
	public void setInput(NumberSource inputSource, int idx);
}
