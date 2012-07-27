package org.chai.memms.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.chai.memms.data.Type;
//import org.chai.memms.value.Value;

public abstract class Line {

	protected static final String SEPARATOR = "-";
	
	private String headerValue;
	private List<String> cssClasses;
	
	private String href = null;
	
	protected Line(String headerValue, List<String> cssClasses) {
		this.headerValue = headerValue;
		this.cssClasses = cssClasses;
	}
	
	public String getHeaderValue() {
		return headerValue;
	}
	
	public List<String> getCssClasses() {
		return cssClasses;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
//	public abstract Value getValueForColumn(int i);
//	
//	public abstract Type getTypeForColumn(int i);
	
	public abstract String getTemplate();
	
	public abstract List<Line> getLines();
	
	public String getDisplayName() {
		List<String> groupsInName = splitName();
		if (groupsInName == null || groupsInName.size() == 0) return null;
		return groupsInName.get(groupsInName.size() - 1);
	}
	
	protected List<String> splitName() {
		if (headerValue == null) return null;
		
		String[] groupsInNameArray = headerValue.split(SEPARATOR);
		List<String> groupsInName = new ArrayList<String>();
		for (String group : groupsInNameArray) {
			groupsInName.add(group.trim());
		}
		return groupsInName;
	}

	protected abstract List<String> getGroups();
	
	protected List<String> getGroupsWithoutPrefix(List<String> prefix) {
		List<String> groups = getGroups();
		if (prefix == null) return groups;
		if (Collections.indexOfSubList(groups, prefix) != 0) throw new IllegalArgumentException();
		else {
			if (groups.size() == 0) return groups;
			return groups.subList(prefix.size(), groups.size());
		}
	}

	@Override
	public String toString() {
		return "Line [headerValue=" + headerValue + "]";
	}
	
}
