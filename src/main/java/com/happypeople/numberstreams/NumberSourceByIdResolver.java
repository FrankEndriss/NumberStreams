package com.happypeople.numberstreams;

/** While deserializing graphs of NumberSource, the NumberSource objects reference each other using IDs.
 * To resolve NumberSource objects by IDs a NumberSourceResolver is used.
 */
public interface NumberSourceByIdResolver {
	public NumberSource resolve(String id);
}
