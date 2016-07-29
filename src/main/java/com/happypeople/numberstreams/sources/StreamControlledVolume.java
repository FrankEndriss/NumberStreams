package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSource;

/** This source combines two streams in a way that the inputSignals volume is
 * controlled by the values of the inputVolume
 */
public class StreamControlledVolume implements NumberSource {

	private NumberSource inputSignal;
	private NumberSource inputVolume;

	public StreamControlledVolume(final NumberSource inputSignal, final NumberSource inputVolume) {
		this.inputSignal=inputSignal;
		this.inputVolume=inputVolume;
	}

	public int read(final double[] buffer) {
		inputSignal.read(buffer);
		final double[] volumeBuffer=new double[buffer.length];
		inputVolume.read(volumeBuffer);

		for(int i=0; i<buffer.length; i++)
			buffer[i]*=volumeBuffer[i];

		return buffer.length;
	}

	public final static int IDX_BASE=0;
	public final static int IDX_VOLUME=1;
	public void setInput(final NumberSource inputSource, final int idx) {
		if(idx==IDX_BASE)
			inputSignal=inputSource;
		else if(idx==IDX_VOLUME)
			inputVolume=inputSource;
		else
			throw new IllegalArgumentException("onyl two inputs are supported, idx="+idx);
	}

}
