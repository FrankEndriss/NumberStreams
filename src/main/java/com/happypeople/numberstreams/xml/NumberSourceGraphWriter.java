package com.happypeople.numberstreams.xml;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.happypeople.numberstreams.NumberSource;
import com.happypeople.numberstreams.NumberSourceMetaResolver;
import com.happypeople.numberstreams.NumberSourceResolver;

public class NumberSourceGraphWriter {

	public void writeGraph(NumberSource[] sinks, StringWriter out) {
		
		NumberSourceMetaResolver metaResolver=new NumberSourceMetaResolver();
		
		NumberSourceResolver resolver=new NumberSourceResolverImpl();
		out.append("<NumberSourceGraph>\n");
		for(NumberSource sink : sinks) {
			out.append(metaResolver.getNumberSourceMeta(sink).toXml(sink, resolver));
		}
		out.append("</NumberSourceGraph>\n");
	}
	
	private class NumberSourceResolverImpl implements NumberSourceResolver {
		private Map<String, NumberSource> mapID2NS=new HashMap<String, NumberSource>();
		private Map<NumberSource, String> mapNS2ID=new HashMap<NumberSource, String>();

		public NumberSource resolve(String id) {
			return null;
		}
		
		public String getId(NumberSource numberSource) {
			String id=mapNS2ID.get(numberSource);
			if(id!=null)
				return id;
			
			if(map.get(numberSource)!=null)
				; // TODO
		}
		
	}
}
