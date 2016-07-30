package com.happypeople.numberstreams.sources.sinus;

import com.happypeople.numberstreams.NumberSourceMeta;

public class SinusMeta implements NumberSourceMeta<Sinus> {

	public Class<Sinus> getNumberSourceType() {
		return Sinus.class;
	}

	public Sinus createNew(final Object[] args) {
		return new Sinus((Double)args[0]);
	}
}
