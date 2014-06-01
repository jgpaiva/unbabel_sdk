package com.unbabel.sdk;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.junit.Test;

public class ApiTest {
   @Test
   public void testTranslation() throws IOException {
      String raw_translation = Utils.fileToString("translation.json");
      Translation translation = Translation.fromJSON(raw_translation);
      assertEquals(translation.uid, "29de9551d9");
   }

   @Test
   public void testLangPair() throws IOException {
      String lang_pairs_raw = Utils.fileToString("lang_pair.json");
      ArrayList<LangPair> lang_pairs = Utils.decodeObjectArray(lang_pairs_raw,
            "lang_pair", LangPair.class);
      assertEquals(lang_pairs.size(), 2);
      assertEquals(lang_pairs.get(0).sourceLanguage.shortName, "pt");
      assertEquals(lang_pairs.get(0).targetLanguage.shortName, "en");
      assertEquals(lang_pairs.get(1).sourceLanguage.shortName, "de");
      assertEquals(lang_pairs.get(1).targetLanguage.shortName, "pt");
   }

   @Test
   public void testTone() throws IOException {
      String tone_raw = Utils.fileToString("tone.json");
      ArrayList<Tone> tone = Utils.decodeObjectArray(tone_raw, "tone",
            Tone.class);
      assertEquals(tone.size(), 4);
      assertEquals(tone.get(0).name, "Informal");
   }

   @Test
   public void testTopics() throws IOException {
      String topics_raw = Utils.fileToString("topics.json");
      ArrayList<Topic> topics = Utils.decodeObjectArray(topics_raw, "topic",
            Topic.class);
      assertEquals(topics.size(), 2);
      assertEquals(topics.get(0).name, "politics");
   }

   @Test
   public void testTranslations() throws IOException {
      String translations_raw = Utils.fileToString("translations.json");
      ArrayList<Translation> translations = Utils.decodeObjectArray(
            translations_raw, "", Translation.class);
      assertEquals(translations.size(), 2);
      assertEquals(translations.get(0).uid, "a281dab6e1");
   }

   public static final String TEST_URL = "http://httpbin.org/post";

   @Test
   public void testPostTranslation() throws Exception {
      UnbabelApi api = new UnbabelApi("testUser", "testKey", false);
      Translation t = new Translation();
      t.sourceLanguage = "en";
      t.targetLanguage = "pt";
      t.text = "some example text";
      String raw_response = api.post(TEST_URL, t.toJSON());
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(raw_response);
      JsonNode headers = rootNode.get("headers");
      JsonNode accept = headers.get("Accept");
      assertEquals(accept.asText(), "application/json");
      JsonNode auth = headers.get("Authorization");
      assertEquals(auth.asText(), "ApiKey testUser:testKey");
      JsonNode contentType = headers.get("Content-Type");
      assertEquals(contentType.asText(), "application/json");
      JsonNode json = rootNode.get("json");
      assertEquals(Translation.fromJSON(json.toString()), t);
   }
}
