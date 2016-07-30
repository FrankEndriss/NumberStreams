package com.happypeople.media;

/** This interface defines a serialization contract. 
 * Classes fullfilling the contract by implementing MediaSerializable.serialize2Media(Media media) and a 
 * constructor expecting a Media object, to read its state from it.
 */
public interface Media {
	/** Add a value to the Media
	 * @param propName
	 * @param propValue
	 */
	public void addProperty(String propName, String propValue);

	/** Add a reference to the media.
	 * @param childName
	 * @param object
	 */
	public void addObject(String refName, Object object);

	/** Reads a property value by name
	 * @param propName
	 * @return the value of the property
	 */
	public String getProperty(String propName);

	/** Reads a referenced Object from the media
	 * @param refName
	 * @return
	 */
	public Media getObject(String refName);
}
