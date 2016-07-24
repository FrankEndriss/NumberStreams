package com.happypeople.numberstreams;

import java.util.HashMap;
import java.util.Map;

import com.happypeople.numberstreams.sources.Sinus;
import com.happypeople.numberstreams.sources.SinusMeta;

/** This service finds a NumberSourceMeta object given a NumberSource object or Class object
 */
public class NumberSourceMetaResolver {
	
	private Map<Class<? extends NumberSource>, NumberSourceMeta<? extends NumberSource>> map=
			new HashMap<Class<? extends NumberSource>, NumberSourceMeta<? extends NumberSource>>();
	
	{
		// TODO addhere more data
		map.put(Sinus.class, new SinusMeta());
	}

	public <T extends NumberSource> NumberSourceMeta<T> getNumberSourceMeta(T numberSource) {
		return (NumberSourceMeta<T>) map.get(numberSource.getClass());
	}

	public <T extends NumberSource> NumberSourceMeta<T> getNumberSourceMeta(Class<T> numberSourceClass) {
		return (NumberSourceMeta<T>) map.get(numberSourceClass);
	}

}
