package com.unbabel.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unbabel.client.RESTClient;
import com.unbabel.sdk.exceptions.ApiException;
import com.unbabel.sdk.exceptions.MarshalingException;

public class UnbabelApi {
	public static final String API_URL = "https://www.unbabel.co/tapi/v2/";
	public static final String SANDBOX_API_URL = "http://sandbox.unbabel.com/tapi/v2/";
	private final String authorization;
	protected String contact_url;

	public UnbabelApi(String username, String apiKey, boolean sandbox) {
		if (username == null || apiKey == null)
			throw new ApiException("username or api key were null", null);

		this.authorization = String.format("ApiKey %s:%s", username, apiKey);
		if (sandbox) {
			this.contact_url = UnbabelApi.SANDBOX_API_URL;
		} else {
			this.contact_url = UnbabelApi.API_URL;
		}
	}

	/**
	 * Posts a translation
	 */
	public Translation postTranslation(Translation translation) {
		String raw_response = post(contact_url + "translation/",
				Utils.objectToJSON(translation));
		return Utils.objectFromJSON(raw_response, Translation.class);
	}

	/**
	 * Bulk posts translations
	 */
	public List<Translation> postTranslations(List<Translation> translations) {
		String raw_response = patch(contact_url + "translation/",
				Utils.encodeObjectArray(translations));
		return Utils.decodeObjectArray(raw_response, "", Translation.class);
	}

	/**
	 * Returns the translations requested by the user
	 */
	public List<Translation> getTranslations() {
		String raw_response = this.get(contact_url + "translation/");
		return Utils.decodeObjectArray(raw_response, "", Translation.class);
	}

	/**
	 * Returns a translation with the given id
	 */
	public Translation getTranslation(String uid) {
		String raw_response = this.get(contact_url + "translation/" + uid);
		return Utils.objectFromJSON(raw_response, Translation.class);
	}

	/**
	 * Returns the language pairs available on unbabel
	 */
	public List<LangPair> getLanguagePairs() {
		return getLanguagePairs(null);
	}

	public List<LangPair> getLanguagePairs(String trainLangs)
			throws MarshalingException {
		String resourceURL = null;
		if (trainLangs == null)
			resourceURL = this.contact_url + "language_pair/";
		else
			resourceURL = String.format("%slanguage_pair/?train_langs=%s",
					this.contact_url, trainLangs);
		String raw_response = this.get(resourceURL);
		return Utils.decodeObjectArray(raw_response, "lang_pair",
				LangPair.class);
	}

	/**
	 * Returns the tones available on unbabel
	 */
	public List<Tone> getTones() {
		String raw_response = this.get(contact_url + "tone/");
		return Utils.decodeObjectArray(raw_response, "tone", Tone.class);
	}

	/**
	 * Returns the tones available on unbabel
	 */
	public List<Topic> getTopics() {
		String raw_response = this.get(contact_url + "topic/");
		return Utils.decodeObjectArray(raw_response, "topic", Topic.class);
	}

	/**
	 * Returns the account details associated with this user
	 */
	public Account getAccount() {
		String raw_response = this.get(contact_url + "account/");
		ArrayList<Account> responseList = Utils.decodeObjectArray(raw_response,
				"account", Account.class);
		if (responseList != null && responseList.size() > 0) {
			return responseList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Creates a new order with no callback url
	 */
	public Order postOrder() {
		return postOrder(null);
	}

	/**
	 * Creates a new order with a given callback url
	 */
	public Order postOrder(String callbackUrl) {
		String raw_response = this.post(contact_url + "order/",
				Utils.encodeStringAsJSON(callbackUrl));
		return Utils.objectFromJSON(raw_response, Order.class);
	}

	public Job postJob(JobRequest request) {
		String raw_response = this.post(contact_url + "job/",
				Utils.objectToJSON(request));
		Job response = Job.fromJSON(raw_response);
		return response;
	}

	/**
	 * Creates a new order with a given callback url
	 */
	public Order payOrder(String orderId) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("order_id", orderId);
		String data_raw = Utils.encodeMapAsJSON(data);
		String raw_response = this.post(contact_url + "pay/", data_raw);
		return Utils.objectFromJSON(raw_response, Order.class);
	}

	public int getWordCount(String text) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("text", text);
		String data_raw = Utils.encodeMapAsJSON(data);
		String raw_response = this.post(contact_url + "wordcount/", data_raw);
		return Integer.valueOf(raw_response);
	}

	protected String get(String resourceURL) {
		return RESTClient.get(resourceURL, this.authorization);
	}

	protected String post(String resourceURL, String json) {
		return RESTClient.post(resourceURL, this.authorization, json);
	}

	protected String patch(String resourceURL, String json) {
		return RESTClient.patch(resourceURL, this.authorization, json);
	}
}
