package com.unbabel.sdk;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Translation {
	@JsonProperty
	protected String uid = null;
	@JsonProperty
	protected String text = null;
	@JsonProperty
	protected String translatedText = null;
	@JsonProperty("target_language")
	protected String targetLanguage = null;
	@JsonProperty("source_language")
	protected String sourceLanguage = null;
	@JsonProperty
	protected String status = null;
	@JsonProperty
	protected ArrayList<String> translators = null;
	@JsonProperty
	protected ArrayList<String> topics = null;
	@JsonProperty
	protected Integer price = null;
	@JsonProperty("text_format")
	protected String textFormat = null;

	@JsonProperty
	protected String client = null;
	@JsonProperty("resource_uri")
	protected String resourceUri = null;
	@JsonProperty
	protected String balance = null;

	protected Translation() {
	}

	public Translation(String text, String sourceLanguage, String targetLanguage) {
		this.text = text;
		this.sourceLanguage = sourceLanguage;
		this.targetLanguage = targetLanguage;
	}

	public String toString() {
		return String.format("%s %s %s_%s", this.uid, this.status,
				this.sourceLanguage, this.targetLanguage);
	}
	
	@JsonCreator
	public static Translation fromJSON(String obj_json){
		return Utils.objectFromJSON(obj_json, Translation.class);
	}

	public boolean equals(Object other) {
		if (!(other instanceof Translation))
			return false;
		Translation o = (Translation) other;
		return o.uid == this.uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTranslatedText() {
		return translatedText;
	}

	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<String> getTranslators() {
		return translators;
	}

	public void setTranslators(ArrayList<String> translators) {
		this.translators = translators;
	}

	public ArrayList<String> getTopics() {
		return topics;
	}

	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getTextFormat() {
		return textFormat;
	}

	public void setTextFormat(String textFormat) {
		this.textFormat = textFormat;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
}
