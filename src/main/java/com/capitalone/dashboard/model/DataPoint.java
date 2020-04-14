package com.capitalone.dashboard.model;

import java.util.ArrayList;
import java.util.List;

public class DataPoint {
	
 

	private List<String> status;
	
	private String name;
	private List<Series> series;
	
	
	private List<Series> organizationSeries = new ArrayList<Series>();

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public List<Series> getOrganizationSeries() {
		return organizationSeries;
	}

	public void setOrganizationSeries(List<Series> organizationSeries) {
		this.organizationSeries = organizationSeries;
	}

	
	
}
