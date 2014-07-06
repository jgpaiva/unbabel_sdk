package com.unbabel.sdk;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class JobRequest {
	@JsonProperty("order_id")
	protected String orderId = null;
	@JsonProperty
	protected String text = null;
	@JsonProperty("target_text")
	protected String targetText = "";
	@JsonProperty("text_format")
	protected String textFormat = "text";
	@JsonProperty("source_language")
	protected String sourceLanguage = null;
	@JsonProperty("target_language")
	protected String targetLanguage = null;
	@JsonProperty
	protected String uid = null;
	@JsonProperty
	protected String tone = null;
	@JsonProperty
	protected List<String> topic = new ArrayList<String>();
	@JsonProperty
	protected String visibility = null;
	@JsonProperty
	protected String instructions = "";
	@JsonProperty("public_url")
	protected String publicUrl = null;
	@JsonProperty("callback_url")
	protected String callbackUrl = null;
	@JsonProperty
	protected String type = "paid";

	protected JobRequest() {
	}

	public JobRequest(String orderId, String text, String sourceLanguage,
			String targetLanguage) {
		this.orderId = orderId;
		this.text = text;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}

	@JsonCreator
	public static JobRequest fromJSON(String obj_json) {
		return Utils.objectFromJSON(obj_json, JobRequest.class);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTargetText() {
		return targetText;
	}

	public void setTargetText(String targetText) {
		this.targetText = targetText;
	}

	public String getTextFormat() {
		return textFormat;
	}

	public void setTextFormat(String textFormat) {
		this.textFormat = textFormat;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTone() {
		return tone;
	}

	public void setTone(String tone) {
		this.tone = tone;
	}

	public List<String> getTopic() {
		return topic;
	}

	public void setTopic(List<String> topic) {
		this.topic = topic;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}