package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSource;

public class SequentialNumberSource implements NumberSource {
	
	private int seq=1;

	public int read(final double[] buffer) {
		for(int i=0; i<buffer.length; i++)
			buffer[i]=seq++;
		return buffer.length;
	}

	public void setInput(final NumberSource inputSource, final int idx) {
		throw new RuntimeException("no input supported");
	}

}
