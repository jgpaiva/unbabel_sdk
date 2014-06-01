package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonProperty;

public class Translator {
   @JsonProperty("first_name")
	public final String firstName;
   @JsonProperty("last_name")
	public final String lastName;
   @JsonProperty("picture_url")
	public final String pictureUrl;
   @JsonProperty("profile_url")
	public final String profileUrl;

	public Translator(String firstName, String lastName, String pictureUrl,
			String profileUrl) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pictureUrl = pictureUrl;
		this.profileUrl = profileUrl;
	}
}