package com.happypeople.numberstreams.sources.mixer;

import com.happypeople.numberstreams.NumberSourceMeta;

public class MixerMeta implements NumberSourceMeta<Mixer> {

	public Class<Mixer> getNumberSourceType() {
		return Mixer.class;
	}

	public Mixer createNew(Object[] args) {
		return new Mixer();
	}

}
