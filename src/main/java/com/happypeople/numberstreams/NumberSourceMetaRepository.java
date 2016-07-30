package com.happypeople.numberstreams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.happypeople.numberstreams.sources.frequencymodulator.FrequencyModulatorMeta;
import com.happypeople.numberstreams.sources.mixer.MixerMeta;
import com.happypeople.numberstreams.sources.sinus.SinusMeta;
import com.happypeople.numberstreams.sources.streamcontrolledvolume.StreamControlledVolumeMeta;
import com.happypeople.numberstreams.sources.volume.VolumeMeta;

/** This service finds a NumberSourceMeta object given a NumberSource object or Class object
 * So, it is the NumberSourceMetaRepository.
 */
public class NumberSourceMetaRepository {
	
	private final List<NumberSourceMeta<? extends NumberSource>> list;

	{
		final List<NumberSourceMeta<? extends NumberSource>> lList=
				new ArrayList<NumberSourceMeta<? extends NumberSource>>();

		lList.add(new SinusMeta());
		lList.add(new VolumeMeta());
		lList.add(new MixerMeta());
		lList.add(new FrequencyModulatorMeta());
		lList.add(new StreamControlledVolumeMeta());

		// TODO add more Metas
		list=Collections.unmodifiableList(lList);
	}

	public List<NumberSourceMeta<? extends NumberSource>> getMetas() {
		return Collections.unmodifiableList(list);
	}
}
