/**
 * 
 */
package com.indream.noteservice.model;

import java.io.Serializable;

/**
 * @author bridgeit
 *
 */
public class UserEntityResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isActive;

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
