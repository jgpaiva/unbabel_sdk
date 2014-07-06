package com.unbabel.sdk;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Order {
	@JsonProperty
	protected String id;
	@JsonProperty
	protected String status;
	@JsonProperty
	protected Integer price;
	@JsonProperty
	protected List<Job> jobs;
	@JsonProperty("callback_url")
	protected String callbackUrl;

	protected Order(){
	}
	
	public Order(String id) {
		this.id = id;
	}

	@JsonCreator
	public static Order fromJSON(String obj_json) {
		return Utils.objectFromJSON(obj_json, Order.class);
	}

	public String toString() {
		return String.format("%s - %s - %s", id, status, price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
}
