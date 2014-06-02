Java SDK for the Unbabel REST API


Documentation:
=============

Please visit our documentation page at https://github.com/Unbabel



Installation
============

This api requires maven and java.
To add as a maven dependency, add the following lines to your pom.xml file:
```xml
<project>
...
    <dependencies>
        <dependency>
            <groupId>com.unbabel</groupId>
            <artifactId>sdk</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
...
    <repositories>
        <repository>
            <id>unbabel_sdk</id>
            <url>https://raw.github.com/jgpaiva/unbabel_sdk/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
...
</project>
```

Getting started:
================

```java
import com.unbabel.sdk.UnbabelApi;
...
UnbabelApi api = new UnbabelApi(username, apikey, isSandbox);
```

## Request a Translation

```java
Translation translation = new Translation();
translation.text = "This is a test translation";
translation.sourceLanguage = "en";
translation.targetLanguage = "pt";
Translation retval = api.postTranslation(translation);
```

## Get a Translation

Returns a translation by its uid.

```java
Translation t = api.getTranslation(uid)
```


## Get all Translations

Returns all the translations for a given user.

```java
List<Translation> translations = api.getTranslations()
```


## Getting Available Language Pairs 

```java
List<LangPair> langPairs = api.getLanguagePairs()
```
  
  Each element of the list is a **LangPair** object that contains a source language and a target language. Each language is an instance of the **Language** class that contains a shortname ( iso639-1 language code ) and a name. 

## Getting Available Tones

```java
List<Tone> tones = api.getTones()
```

Each element of the list is a **Tone** object that contains the name and the description of the Tone.
