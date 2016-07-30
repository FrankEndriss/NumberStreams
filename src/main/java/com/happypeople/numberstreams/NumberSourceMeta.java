package com.happypeople.numberstreams;

/** Meta functionality of NumberSource, ie Serialization
 * @param <T> the NumberSource class this NumberSourceMeta offers services
 */
public interface NumberSourceMeta<T extends NumberSource> {
	
	/**
	 * @return the class object of the NumberSource
	 */
	public Class<T> getNumberSourceType();
	
	/** Creates a new object of type t, initialized to given args values.
	 * Could be modeled "nicer" if we create/define a constructor-argument-type, and use this here.
	 * Or create a type ConstructorArgumentDescriptor, and use that. But this seems like overkill...
	 * Java reflection, and others like spring bean instantiation use simply a Object[] arg, too.
	 * @return the new NumberSource object
	 */
	public T createNew(Object[] args);

}
