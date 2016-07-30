package com.happypeople.numberstreams.sources.streamcontrolledvolume;

import com.happypeople.numberstreams.NumberSourceMeta;

public class StreamControlledVolumeMeta implements NumberSourceMeta<StreamControlledVolume> {

	public Class<StreamControlledVolume> getNumberSourceType() {
		return StreamControlledVolume.class;
	}

	public StreamControlledVolume createNew(Object[] args) {
		return new StreamControlledVolume();
	}

}
