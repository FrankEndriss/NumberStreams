package com.happypeople.media;

/** Interface for Objects capable of serializing themselfs to a Media.
 * As a convenience, the classes of these Objects should implement a 
 * public single argument constructor getting a Media object, and
 * then read its state from that media object.
 */
public interface MediaSerializeable {
	public void serialize2Media(Media media);
}
