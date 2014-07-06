package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Job {
	@JsonProperty("id")
	protected String id = null;
	@JsonProperty("uid")
	protected String uid = null;
	@JsonProperty("order")
	protected Order orderId = null;
	@JsonProperty
	protected String status = null;
	@JsonProperty
	protected String text = null;
	@JsonProperty
	protected Integer price = null;
	@JsonProperty("source_language")
	protected String sourceLanguage = null;
	@JsonProperty("target_language")
	protected String targetLanguage = null;
	@JsonProperty
	protected String tone = null;
	@JsonProperty("text_format")
	protected String textFormat = null;
	
	@JsonCreator
	public static Job fromJSON(String obj_json){
		return Utils.objectFromJSON(obj_json, Job.class);
	}

	public String toString() {
		return String.format("%s - %s - %s", orderId, id, status);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Order getOrderId() {
		return orderId;
	}

	public void setOrderId(Order orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public String getTone() {
		return tone;
	}

	public void setTone(String tone) {
		this.tone = tone;
	}

	public String getTextFormat() {
		return textFormat;
	}

	public void setTextFormat(String textFormat) {
		this.textFormat = textFormat;
	}
}
