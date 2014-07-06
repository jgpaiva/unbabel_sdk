package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * An account associated with an API user
 * 
 * @author jgpaiva
 * 
 */
public class Account {
	@JsonProperty
	protected String username;
	@JsonProperty
	protected String email;
	@JsonProperty
	protected String balance;

	@JsonCreator
	public static Account fromJSON(String obj_json) {
		return Utils.objectFromJSON(obj_json, Account.class);
	}

	public String toString() {
		return String
				.format("email: %s, balance: %s", this.email, this.balance);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
}