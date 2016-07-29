package com.happypeople.numberstreams.sources;

import java.util.ArrayList;
import java.util.List;

import com.happypeople.numberstreams.NumberSource;

/** A DynamicSubGraph is a NumberSource which aggregates other NumberSources.
 * It has a dynamic number of inputs, and one output.
 * It can connect (and disconnect) NumberSource objects contained in it, which make up a 
 * graph of NumberSources.
 * Since a DynamicSubGraph is a NumberSource itself, it can contain other DynamicSubGraph
 * objects.
 */
public class DynamicSubGraph implements NumberSource {

	/** This is the last (lowest) NumberSource object in this graph. */
	private NumberSource output;
	
	private final List<Connection> connectionList=new ArrayList<Connection>();

	public int read(final double[] buffer) {
		return output.read(buffer);
	}
	
	public void connect(final NumberSource input, final NumberSource target, final int idx) {
		final Connection con=new Connection();
		con.input=input;
		con.connectedTo=target;
		con.atIdx=idx;
		connectionList.add(con);
		target.setInput(input, idx);
	}

	public void setInput(final NumberSource inputSource, final int idx) {
		// TODO implement usefull editor features...
		// still not sure what usefull is :/
	}
	
	
	/** Connection connect input to connectedTo at idx atIdx
	 */
	private static class Connection {
		NumberSource input;
		NumberSource connectedTo;
		int atIdx;
	}

}
