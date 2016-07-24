package com.happypeople.numberstreams;

/** A stream of numbers, endless by definition.
 */
public interface NumberSource {
	/** Read numbers
	 * @param buffer is filled with numbers by the call to this method.
	 * @return the count of numbers actually written to buffer. Like InputStream.read(buf)
	 */
	public int read(double[] buffer);
	
	/** Setting the source for the input of this NumberSource.
	 * Some NumberSource may not use any input, some exactly one input (or another number), some
	 * a dynamic number.
	 * @param inputSource the source to use as input
	 * @param idx the index of the "input channel", starting at 0
	 */
	public void setInput(NumberSource inputSource, int idx);
}
