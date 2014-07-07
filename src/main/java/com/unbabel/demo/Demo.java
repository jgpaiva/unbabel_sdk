package com.unbabel.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import com.unbabel.sdk.LangPair;
import com.unbabel.sdk.Tone;
import com.unbabel.sdk.Topic;
import com.unbabel.sdk.Translation;
import com.unbabel.sdk.UnbabelApi;

/**
 * demo for unbabel API. run with 'mvn clean compile exec:java
 * -Dexec.args="USERNAME APIKEY"'
 * 
 * @author jgpaiva
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		final UnbabelApi api = new UnbabelApi(args[0], args[1], true);

		List<LangPair> langPairs = api.getLanguagePairs();
		System.out.println("Language Pairs:" + langPairs);

		List<Tone> tones = api.getTones();
		System.out.println("Tones: " + tones);

		List<Topic> topics = api.getTopics();
		System.out.println("Topics: " + topics);

		Translation translation = new Translation("Some text", "en", "pt");
		final Translation retval = api.postTranslation(translation);
		System.out.println("Immediate response: " + retval);

		repeatTwoTimes(new Callable<Object>() {
			public Object call() throws Exception {
				return api.getTranslation(retval.getUid());
			}
		});

		List<Translation> translations = new ArrayList<Translation>();
		for (String text : Arrays.asList("First text", "Second text",
				"Third text")) {
			Translation t = new Translation(text, "en", "pt");
			translations.add(t);
		}
		final List<Translation> translationsReply = api
				.postTranslations(translations);
		for (Translation t : translationsReply) {
			System.out.println("Immediate response: " + t);
		}
		repeatTwoTimes(new Callable<Object>() {
			public Object call() throws Exception {
				List<Translation> retval = new ArrayList<Translation>();
				for (Translation t : translationsReply) {
					retval.add(api.getTranslation(t.getUid()));
				}
				return retval;
			}
		});

		List<Translation> retvals = api.getTranslations();
		System.out.println("all translations: " + retvals);
	}

	public static void repeatTwoTimes(Callable<Object> r) {
		try {

			Thread.sleep(4000);
			Object result = r.call();
			System.out.println("Response after 4 sec: " + result);
			Thread.sleep(60000);
			result = r.call();
			System.out.println("Response after 60 sec: " + result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
