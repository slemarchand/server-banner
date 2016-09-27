package com.slemarchand.serverbanner.model;

import java.util.List;

public class Configuration {
	
	private boolean enabled;
	
	private List<String> themeIds;
	
	private List<String> roleNames;

	private String message;
	
	private String color;
	
	private String backgroundColor;
	
	private String size;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getThemeIds() {
		return themeIds;
	}

	public void setThemeIds(List<String> themeIds) {
		this.themeIds = themeIds;
	}

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> rolesNames) {
		this.roleNames = rolesNames;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
