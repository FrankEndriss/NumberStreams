package com.happypeople.numberstreams;

/** While deserializing graphs of NumberSource, the NumberSource objects reference each other using IDs.
 * To resolve NumberSource objects by IDs a NumberSourceResolver is used.
 */
public interface NumberSourceResolver {
	public NumberSource resolve(String id);
}
