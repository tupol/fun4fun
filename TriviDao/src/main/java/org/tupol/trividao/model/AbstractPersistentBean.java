package org.tupol.trividao.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Abstract implementation of the PersistentBean interface.
 * 
 * We assume the key to be a string, preferably a UUID.
 * 
 * @see PersistentBean
 * 
 * @author tupol
 * 
 */
public abstract class AbstractPersistentBean implements PersistentBean {

	private static final long serialVersionUID = 9187628654428910569L;

	/**
	 * The id is used as a primary key in the database mapping
	 */
	protected String id;

	/**
	 * @see #id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see #id
	 */
	public String getId() {
		return id;
	}

	/**
	 * As Joshua Bloch and the Hibernate documentation teaches us, equals and
	 * hashcode are a must.
	 * <p>
	 * Apache did a good job with equals builder so let's use it as a default
	 * and simplify if needed. Knowing that I am lazy, I'll probably forget, so
	 * it's good to have it here.
	 */
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, true);
	}

	/**
	 * As Joshua Bloch and the Hibernate documentation teaches us, equals and
	 * hashcode are a must.
	 * <p>
	 * Apache did a good job with hashCode builder so let's use it as a default
	 * and simplify if needed. Knowing that I am lazy, I'll probably forget, so
	 * it's good to have it here.
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, true);
	}

	/**
	 * Simple toString implementation
	 */
	@Override
	public String toString() {
		// Always a good idea to override toString in this context
		return String
				.format("%s; id='%s'", this.getClass().getSimpleName(), id);
	}

}
