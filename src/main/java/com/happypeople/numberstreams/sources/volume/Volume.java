package com.happypeople.numberstreams.sources.volume;

import com.happypeople.numberstreams.NumberSource;

/** A AdjustableVolume object can be used to set the volume of another NumberSource.
 */
public class Volume implements NumberSource {
	private static final long serialVersionUID = 1L;

	private NumberSource input;
	private final double multiplicator;

	public Volume(final double volume) {
		this.multiplicator=volume;
	}

	public int read(final double[] buffer) {
		final int len=input.read(buffer);
		for(int i=0; i<len; i++)
			buffer[i]*=multiplicator;
		return len;
	}
	
	public void setInput(final NumberSource inputSource, final int idx) {
		if(idx==0)
			this.input=inputSource;
		else
			throw new IllegalArgumentException("supporting only one input");
	}
}
