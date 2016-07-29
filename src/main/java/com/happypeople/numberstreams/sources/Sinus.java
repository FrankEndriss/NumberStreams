package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSource;

public class Sinus implements NumberSource {
	
	private final double lenOfFullCircle;
	private final double lenOfFullCircleDivPI;
	private double lastOffset=-0.5;

	/**
	 * @param lenOfFullCircle count of samples of one full sinus wave. should not be less than two for
	 * obvious reasons.
	 */
	public Sinus(final double lenOfFullCircle) {
		this.lenOfFullCircle=lenOfFullCircle;
		this.lenOfFullCircleDivPI=lenOfFullCircle/(2*Math.PI);
	}

	public int read(final double[] buffer) {
		for(int i=0; i<buffer.length; i++) {
			lastOffset+=1;
			if(lastOffset>=lenOfFullCircle)
				lastOffset-=lenOfFullCircle;

			buffer[i]=Math.sin(lastOffset/lenOfFullCircleDivPI);
		}
		return buffer.length;
	}

	public void setInput(final NumberSource inputSource, final int idx) {
		throw new IllegalArgumentException("Sinus does not support inputs");
	}

}
