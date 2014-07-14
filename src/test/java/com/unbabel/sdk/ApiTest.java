package com.unbabel.sdk;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class ApiTest {
   @Test
   public void testTranslation() throws IOException {
      String raw_translation = Utils.fileToString("translation.json");
      Translation translation = Translation.fromJSON(raw_translation);
      assertEquals("89500a6e77", translation.uid);
   }

   @Test
   public void testLangPair() throws IOException {
      String lang_pairs_raw = Utils.fileToString("lang_pair.json");
      ArrayList<LangPair> lang_pairs = Utils.decodeObjectArray(lang_pairs_raw,
            "lang_pair", LangPair.class);
      assertEquals(2, lang_pairs.size());
      assertEquals("pt", lang_pairs.get(0).sourceLanguage.shortName);
      assertEquals("en", lang_pairs.get(0).targetLanguage.shortName);
      assertEquals("de", lang_pairs.get(1).sourceLanguage.shortName);
      assertEquals("pt", lang_pairs.get(1).targetLanguage.shortName);
   }

   @Test
   public void testTone() throws IOException {
      String tone_raw = Utils.fileToString("tone.json");
      ArrayList<Tone> tone = Utils.decodeObjectArray(tone_raw, "tone",
            Tone.class);
      assertEquals(4, tone.size());
      assertEquals("Informal", tone.get(0).name);
   }

   @Test
   public void testTopics() throws IOException {
      String topics_raw = Utils.fileToString("topics.json");
      ArrayList<Topic> topics = Utils.decodeObjectArray(topics_raw, "topic",
            Topic.class);
      assertEquals(2, topics.size());
      assertEquals("politics", topics.get(0).name);
   }

   @Test
   public void testTranslations() throws IOException {
      String translations_raw = Utils.fileToString("translations.json");
      ArrayList<Translation> translations = Utils.decodeObjectArray(
            translations_raw, "", Translation.class);
      assertEquals(2, translations.size());
      assertEquals("a281dab6e1", translations.get(0).uid);
   }

   public static final String TEST_URL = "http://httpbin.org/post";

   @Test
   public void testPostTranslation() throws Exception {
      UnbabelApi api = new UnbabelApi("testUser", "testKey", false);
      Translation t = new Translation();
      t.sourceLanguage = "en";
      t.targetLanguage = "pt";
      t.text = "some example text";
      String raw_response = api.post(TEST_URL, Utils.objectToJSON(t));
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(raw_response);
      JsonNode headers = rootNode.get("headers");
      JsonNode accept = headers.get("Accept");
      assertEquals("application/json", accept.asText());
      JsonNode auth = headers.get("Authorization");
      assertEquals("ApiKey testUser:testKey", auth.asText());
      JsonNode contentType = headers.get("Content-Type");
      assertEquals("application/json", contentType.asText());
      JsonNode json = rootNode.get("json");
      assertEquals(t, Translation.fromJSON(json.toString()));
   }
   
   @Test
   public void testAccount() throws IOException {
      String account_raw = Utils.fileToString("account.json");
      Account account = Account.fromJSON(account_raw);
      assertEquals("jgpaiva@gmail.com", account.email);
   }
   
   @Test
   public void testOrder() throws IOException {
      String order_raw = Utils.fileToString("order.json");
      Order order = Order.fromJSON(order_raw);
      assertEquals("New", order.status);
   }
}
