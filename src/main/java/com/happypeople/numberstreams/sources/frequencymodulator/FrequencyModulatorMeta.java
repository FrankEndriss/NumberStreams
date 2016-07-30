package com.happypeople.numberstreams.sources.frequencymodulator;

import com.happypeople.numberstreams.NumberSourceMeta;

public class FrequencyModulatorMeta implements NumberSourceMeta<FrequencyModulator> {

	public Class<FrequencyModulator> getNumberSourceType() {
		return FrequencyModulator.class;
	}

	public FrequencyModulator createNew(Object[] args) {
		return new FrequencyModulator((Integer)args[0]);
	}

}
