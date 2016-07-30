package com.happypeople.numberstreams.sources.volume;

import com.happypeople.numberstreams.NumberSourceMeta;

public class VolumeMeta implements NumberSourceMeta<Volume> {

	public Class<Volume> getNumberSourceType() {
		return Volume.class;
	}

	public Volume createNew(Object[] args) {
		return new Volume((Double)args[0]);
	}

}
