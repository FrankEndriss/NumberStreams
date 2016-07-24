package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSource;

/** This NumberSource uses two inputs, and modulates the first using the values from the second.
 * See http://wikipedia.de/Frequenzmodulation
 */
public class FrequencyModulator implements NumberSource {

	private BufferedNumberSource base;
	private NumberSource mod;
	private final int minModulatorValue;

	/**
	 * @param inputBase the base signal which is modulated, ie a sinus wave
	 * @param inputModulator the modulator
	 */
	public FrequencyModulator(final int minModulatorValue) {
		this.minModulatorValue=minModulatorValue;
	}

	public int read(final double[] buffer) {
		final double[] modBuffer=new double[buffer.length];
		final int len=mod.read(modBuffer);
		
		for(int i=0; i<len; i++) {
			final int leftIdx=(int) Math.floor(modBuffer[i]);
			final int rightIdx=leftIdx+1;
			final double fraction=modBuffer[i]-leftIdx;	// fraction is always positive and less than 1
			
			// create linear middle value of those both values
			final double val1=base.read(leftIdx, false);
			final double val2=base.read(rightIdx, true);
			buffer[i]=(val1*fraction)+(val2*(1-fraction));
		}
		
		return len;
	}
	
	public final static int IDX_BASE=0;
	public final static int IDX_MOD=1;

	public void setInput(NumberSource inputSource, int idx) {
		if(idx==IDX_BASE)  {
			this.base=new BufferedNumberSource(minModulatorValue);
			this.base.setInput(inputSource, 0);
		} else if(idx==IDX_MOD)
			this.mod=inputSource;
		else
			throw new RuntimeException("not supported idx="+idx);
	}

}
