package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class LangPair {
	@JsonProperty("source_language")
	protected Language sourceLanguage;
	@JsonProperty("target_language")
	protected Language targetLanguage;

	@JsonCreator
	public static LangPair fromJSON(String obj_json){
		return Utils.objectFromJSON(obj_json, LangPair.class);
	}

	public String toString() {
		return String.format("%s_%s", sourceLanguage, targetLanguage);
	}

	public Language getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(Language sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public Language getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(Language targetLanguage) {
		this.targetLanguage = targetLanguage;
	}
}