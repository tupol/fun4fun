package org.tupol.trividao.model;

import java.io.Serializable;

/**
 * This interface defines the key features of any bean to be persisted.
 * <p>
 * For most of the cases that is a primary key, called <code>id</code> in this
 * context.
 * <p>
 * We assume the key to be a string, preferably a UUID.
 * 
 * @author tupol
 * 
 */
public interface PersistentBean extends Serializable {

	public void setId(String id);

	public String getId();

}
