package com.unbabel.demo;

import java.util.List;

import com.unbabel.sdk.LangPair;
import com.unbabel.sdk.Tone;
import com.unbabel.sdk.Topic;
import com.unbabel.sdk.Translation;
import com.unbabel.sdk.UnbabelApi;

/**
 * demo for unbabel API. run with 'mvn clean compile exec:java -Dexec.args="USERNAME APIKEY"'
 * @author jgpaiva
 */
public class Demo {
   public static void main(String [] args) throws Exception{
      UnbabelApi api = new UnbabelApi(args[0], args[1], true);
      
      List<LangPair> langPairs = api.getLanguagePairs();
      System.out.println("lang pairs:" + langPairs);
      
      List<Tone> tones = api.getTones();
      System.out.println("tones: " + tones);
      
      List<Topic> topics = api.getTopics();
      System.out.println("topics: " + topics);
      
      Translation translation = new Translation("Some text", "en", "pt");
      Translation retval = api.postTranslation(translation);
      System.out.println("response: " + retval);
      Thread.sleep(4000);
      retval = api.getTranslation(retval.getUid());
      System.out.println("response after 4 sec: " + retval);
      Thread.sleep(60000);
      retval = api.getTranslation(retval.getUid());
      System.out.println("response after 60 sec: " +retval);
      List<Translation> retvals = api.getTranslations();
      System.out.println("all translations: " +retvals);
   }
}
