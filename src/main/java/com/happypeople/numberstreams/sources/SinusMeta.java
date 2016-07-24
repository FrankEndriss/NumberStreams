package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSourceMeta;
import com.happypeople.numberstreams.NumberSourceResolver;

public class SinusMeta implements NumberSourceMeta<Sinus> {

	public String getNumberSourceType() {
		return "Sinus";
	}

	public CharSequence toXml(Sinus numberSource, NumberSourceResolver resolver) {
		// TODO implement
		return null;
	}

	public Sinus toNumberSource(String xml, NumberSourceResolver resolver) {
		// TODO implement
		return null;
	}

}
