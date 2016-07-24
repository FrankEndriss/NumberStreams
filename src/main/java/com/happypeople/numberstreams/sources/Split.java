package com.happypeople.numberstreams.sources;

import java.util.LinkedList;

import com.happypeople.numberstreams.NumberSource;

/** A Split is a construct to double NumberSourceStreams.
 * The static methos createSplits takes one input object, and creates
 * an arbitrary number of NumberSource which all return the same
 * numbers as the input object.
 */
public class Split implements NumberSource {

	private InputBuffer input;
	private LinkedList<double[]> bufferList=new LinkedList<double[]>();

	private Split(InputBuffer input) {
		this.input=input;
	}
	
	private void addBuffer(double[] buffer) {
		bufferList.add(buffer);
	}

	public int read(double[] buffer) {
		if(bufferList.size()==0)
			input.fetchNext(buffer.length);

		double[] sigBuffer=bufferList.removeFirst();
		int ret=Math.min(sigBuffer.length, buffer.length);
		// TODO warn about different lengths of sigBuffer and buffer, since that would cause
		// data loss!
		// Or better: implement handling of that case.
		System.arraycopy(sigBuffer, 0, buffer, 0, ret);
		return ret;
	}

	public static NumberSource[] createSplits(NumberSource input, int splitCount) {
		Split[] ret=new Split[splitCount];
		InputBuffer inputBuffer=new InputBuffer(input, ret);
		
		for(int i=0; i<splitCount; i++)
			ret[i]=new Split(inputBuffer);
		
		return ret;
	}
	
	private static class InputBuffer {
		private NumberSource source;
		private Split[] splits;


		InputBuffer(NumberSource source, Split[] splits) {
			this.source=source;
			this.splits=splits;
		}
		
		public void fetchNext(int size) {
			final double[] signalBuffer=new double[size];
			source.read(signalBuffer);
			
			for(Split split : splits)
				split.addBuffer(signalBuffer);
		}
		
		public void setInput(NumberSource input) {
			this.source=input;
		}
	}

	public void setInput(NumberSource inputSource, int idx) {
		if(idx==0)
			input.setInput(inputSource);
		else
			throw new IllegalArgumentException("Only one input supported, idx="+idx);
	}
}
