package com.happypeople.numberstreams.sources;

import com.happypeople.numberstreams.NumberSourceMeta;
import com.happypeople.numberstreams.NumberSourceResolver;

public class SinusMeta implements NumberSourceMeta<Sinus> {

	public String getNumberSourceType() {
		return "Sinus";
	}

	public CharSequence toXml(final Sinus numberSource, final NumberSourceResolver resolver) {
		// TODO implement
		return null;
	}

	public Sinus toNumberSource(final String xml, final NumberSourceResolver resolver) {
		// TODO implement
		return null;
	}

}
