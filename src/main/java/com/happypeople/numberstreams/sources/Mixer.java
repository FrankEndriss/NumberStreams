package com.happypeople.numberstreams.sources;

import java.util.HashMap;
import java.util.Map;

import com.happypeople.numberstreams.NumberSource;

/** A Mixer mixes up a group of NumberSource into one NumberSource
 */
public class Mixer implements NumberSource {

	private final Map<Integer, NumberSource> inputs=new HashMap<Integer, NumberSource>();

	public int read(final double[] buffer) {

		int idx=0;
		final double[] lBuf=new double[buffer.length];
		for(NumberSource input : inputs.values()) {
			if(idx==0)
				input.read(buffer);
			else {
				input.read(lBuf);
				for(int i=0; i<lBuf.length; i++)
					buffer[i]+=lBuf[i];
			}
		}

		return buffer.length;
	}

	public void setInput(NumberSource inputSource, int idx) {
		inputs.put(idx, inputSource);
	}

}
