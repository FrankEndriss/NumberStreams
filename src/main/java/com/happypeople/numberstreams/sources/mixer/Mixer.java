package com.happypeople.numberstreams.sources.mixer;

import java.util.HashMap;
import java.util.Map;

import com.happypeople.numberstreams.NumberSource;

/** A Mixer mixes up a group of NumberSource into one NumberSource
 */
public class Mixer implements NumberSource {
	private static final long serialVersionUID = 1L;

	private final Map<Integer, NumberSource> inputs=new HashMap<Integer, NumberSource>();

	public int read(final double[] buffer) {

		boolean firstLoop=true;
		final double[] lBuf=new double[buffer.length];
		for(final NumberSource input : inputs.values()) {
			if(firstLoop) {
				input.read(buffer);
				firstLoop=false;
			} else {
				input.read(lBuf);
				for(int i=0; i<lBuf.length; i++)
					buffer[i]+=lBuf[i];
			}
		}

		return buffer.length;
	}

	public void setInput(final NumberSource inputSource, final int idx) {
		if(inputSource!=null)
			inputs.put(idx, inputSource);
		else
			inputs.remove(idx);
	}

}
