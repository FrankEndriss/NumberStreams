package com.happypeople.numberstreams.sources;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.happypeople.numberstreams.NumberSource;
import com.happypeople.numberstreams.sources.mixer.Mixer;

/** A DynamicSubGraph is a NumberSource which aggregates other NumberSources.
 * It can connect (and disconnect) NumberSource objects contained in it, which make up a 
 * graph of NumberSources.
 * Since a DynamicSubGraph is a NumberSource itself, it can contain other DynamicSubGraph
 * objects.
 */
public class DynamicSubGraph implements NumberSource {
	private static final long serialVersionUID = 1L;

	/** This is the last (lowest) NumberSource object in this graph. */
	private Mixer output=new Mixer();
	private final List<Connection> connectionList=new LinkedList<Connection>();

	public int read(final double[] buffer) {
		return output.read(buffer);
	}
	
	public void disconnect(final NumberSource source) {
		for(Iterator<Connection> iter = connectionList.iterator(); iter.hasNext();) {
			Connection con=iter.next();
			if(con.input==source) {
				iter.remove();
				con.connectedTo.setInput(null, con.atIdx);
				return;
			}
		}
	}

	public void disconnect(final NumberSource target, final int idx) {
		for(Iterator<Connection> iter = connectionList.iterator(); iter.hasNext();) {
			Connection con=iter.next();
			if(con.connectedTo==target && con.atIdx==idx) {
				iter.remove();
				target.setInput(null, idx);
				return;
			}
		}
	}

	public void connect(final NumberSource input, final NumberSource target, final int idx) {
		final Connection con=new Connection();
		con.input=input;
		con.connectedTo=target;
		con.atIdx=idx;
		target.setInput(input, idx);
		connectionList.add(con);
	}

	public void setInput(NumberSource inputSource, int idx) {
		throw new RuntimeException(getClass().getName()+" still does not support inputs");
	}

	/** Connection connect input to connectedTo at idx atIdx
	 * it means: connectedTo.setInput(input, atIdx)
	 */
	private static class Connection implements Serializable {
		private static final long serialVersionUID = 1L;

		NumberSource input;
		NumberSource connectedTo;
		int atIdx;
	}

}
