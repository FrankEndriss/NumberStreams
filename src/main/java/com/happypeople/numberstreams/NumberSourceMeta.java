package com.happypeople.numberstreams;

/** Meta functionality of NumberSource, ie Serialization
 * @param <T> the NumberSource class this NumberSourceMeta offers services
 */
public interface NumberSourceMeta<T extends NumberSource> {
	
	/**
	 * @return the type specifier of <T> used in XML serialization, usually the classname
	 */
	public String getNumberSourceType();
	
	/** Creates the xml of NumberSource numberSource
	 * @param numberSource the NumberSource to serialize
	 * @param resolver the resolver to get/create IDs of referenced NumberSources
	 * @return
	 */
	public CharSequence toXml(T numberSource, NumberSourceResolver resolver);
	
	/** Called to deserialize a NumberSource object of type getNumberSourceType()
	 * @param xml the xml written by toXml(...)
	 * @param resolver resolver used to resolve references
	 * @return the newly created NumberSource object
	 */
	public T toNumberSource(String xml, NumberSourceResolver resolver);

}
