package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSource;

/** This class adds buffering capabilities to a NumberSource.
 * One can re-read values from a buffer, and read ahead using the read(int offset) method.
 */
public class BufferedNumberSource implements NumberSource {
	private NumberSource source;
	private final int minLeftBufferSize;
	private double[] internalBuffer;
	
	/** idx0 is the index into internalBuffer where the current data starts. */
	private int idx0;
	/** aheadBuffered is the count of values in internalBuffer starting at idx0 */
	private int aheadBuffered=0;
	
	/** when reading from source, use buffers of this size */
	public final static int READ_AHEAD_BUFFERSIZE=1024;

	public BufferedNumberSource(final int minLeftBufferSize) {
		this.minLeftBufferSize=minLeftBufferSize;
		
		// initialize the internal buffer to all 0.0
		internalBuffer=new double[-minLeftBufferSize+1];
		idx0=-minLeftBufferSize;
	}

	public int read(final double[] buffer) {
		return source.read(buffer);
	}
	
	/** Read the next value from the stream, but use the value at index shifted by offset.
	 * The call increases the internal index by one, as a call to read(double[]) would do if 
	 * called with an array of length 1.
	 * @param offset must not be less than minLeftBufferSize
	 * @return the next value
	 */
	public double read(final int offset, final boolean doIncrement) {
		if(offset<minLeftBufferSize)
			throw new IllegalArgumentException("offset must not be less than minLeftBufferSize: offset="+offset+" minLeftBufferSize="+minLeftBufferSize);
		
		if(offset>=aheadBuffered) // we need to read and buffer more data from source...
			cleanupBuffer(idx0+offset);
		
		final double ret=internalBuffer[idx0+offset];

		if(doIncrement) {
			idx0++;
			aheadBuffered--;
		}
		return ret;
	}

	private void cleanupBuffer(int neededIdx) {
		// step 1: remove data to the left as far as possible
		if(idx0>-minLeftBufferSize) {
			System.arraycopy(internalBuffer, idx0+minLeftBufferSize, internalBuffer, 0, aheadBuffered-minLeftBufferSize);
			neededIdx-=(idx0+minLeftBufferSize);
			idx0=-minLeftBufferSize;
		}
		
		// step 2: check if neededIdx is out of internalBuffer.lenth, if so reallocate
		int newSize=idx0+aheadBuffered;
		if(newSize<=neededIdx) 
			newSize+=READ_AHEAD_BUFFERSIZE;
		if(newSize<=neededIdx) 
			newSize=neededIdx+1;

		if(internalBuffer.length<newSize) {
			final double[] newBuffer=new double[newSize];
			System.arraycopy(internalBuffer, 0, newBuffer, 0, idx0+aheadBuffered);
			internalBuffer=newBuffer;
		}
		
		// step 3: ...and read data
		final double[] tmpBuffer=new double[newSize-(idx0+aheadBuffered)];
		source.read(tmpBuffer);
		System.arraycopy(tmpBuffer, 0, internalBuffer, idx0+aheadBuffered, tmpBuffer.length);
		aheadBuffered+=tmpBuffer.length;

	}

	public void setInput(final NumberSource inputSource, final int idx) {
		if(idx==0)
			source=inputSource;
		else
			throw new IllegalArgumentException("only one input supported, idx="+idx);
	}
}
