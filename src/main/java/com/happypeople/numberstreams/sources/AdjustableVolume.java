package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSource;

/** A AdjustableVolume object can be used to set the volume of another NumberSource.
 */
public class AdjustableVolume implements NumberSource {
	private NumberSource input;
	private double multiplicator=1;

	public AdjustableVolume(final NumberSource input) {
		this.input=input;
	}

	public int read(final double[] buffer) {
		final int len=input.read(buffer);
		for(int i=0; i<len; i++)
			buffer[i]*=multiplicator;
		return len;
	}
	
	public void setInput(NumberSource inputSource, int idx) {
		if(idx==0)
			this.input=inputSource;
		else
			throw new IllegalArgumentException("supporting only one input");
	}

	/** This sets the volume as a multiplicator to the original signal
	 * @param multiplicator
	 */
	public void setMultiplicator(final double multiplicator) {
		this.multiplicator=multiplicator;
	}

}
