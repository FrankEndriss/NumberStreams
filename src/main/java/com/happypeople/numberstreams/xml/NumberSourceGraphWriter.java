package com.happypeople.numberstreams.xml;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.happypeople.numberstreams.NumberSource;
import com.happypeople.numberstreams.NumberSourceMetaResolver;
import com.happypeople.numberstreams.NumberSourceResolver;

public class NumberSourceGraphWriter {
	
	private final AtomicInteger idGenerator=new AtomicInteger(1);

	public void writeGraph(final NumberSource[] sinks, final StringWriter out) {
		
		final NumberSourceMetaResolver metaResolver=new NumberSourceMetaResolver();
		
		final NumberSourceResolver resolver=new NumberSourceResolverImpl();
		out.append("<NumberSourceGraph>\n");
		for(final NumberSource sink : sinks) {
			out.append(metaResolver.getNumberSourceMeta(sink).toXml(sink, resolver));
		}
		out.append("</NumberSourceGraph>\n");
	}
	
	private class NumberSourceResolverImpl implements NumberSourceResolver {
		private final Map<String, NumberSource> mapID2NS=new HashMap<String, NumberSource>();
		private final Map<NumberSource, String> mapNS2ID=new HashMap<NumberSource, String>();

		public NumberSource resolve(final String id) {
			return null;
		}
		
		public String getId(final NumberSource numberSource) {
			final String id=mapNS2ID.get(numberSource);
			if(id!=null)
				return id;
			
			final String newId=""+idGenerator.getAndIncrement();
			mapNS2ID.put(numberSource, newId);
			mapID2NS.put(newId, numberSource);
			return newId;
		}
		
	}
}
