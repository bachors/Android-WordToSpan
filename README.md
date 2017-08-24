# Android-WordToSpan
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WordToSpan-red.svg?style=flat)](https://android-arsenal.com/details/1/6101)
[![Release](https://jitpack.io/v/bachors/Android-WordToSpan.svg)](https://jitpack.io/#bachors/Android-WordToSpan)

Small &amp; simple library to create a link url, mail, mention, tag and text highlighter in textView.

Gradle
------
```
allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
```
```
dependencies {
    ...
    compile 'com.github.bachors:Android-WordToSpan:2.0'
}
```

Usage
-----
```java
...

WordToSpan WTS = new WordToSpan();
```

Create Link
-----
![gif](http://i.giphy.com/3o7qiVzv4pGAbBaCUU.gif)

```java
// set color link. Default = Color.BLUE
WTS.setColorTAG(Color.GREEN);
WTS.setColorURL(Color.MAGENTA);
WTS.setColorMAIL(getResources().getColor(R.color.colorPrimary));
WTS.setColorMENTION(getResources().getColor(R.color.colorAccent));

/* add custom
// WTS.setRegexCUSTOM("([0-9]+-[0-9]+)");
// WTS.setColorCUSTOM(Color.YELLOW);
*/

// set underline link. Default = false
// WTS.setUnderlineTAG(true);
WTS.setUnderlineURL(true);

// create link
String myText = "I know http://just.com/anu how to @whisper, And I #know just #how to cry,I know just @where to anu@find.com the answers";
TextView TV = (TextView)findViewById(R.id.txt);
WTS.setLink(myText, TV);

// click listener
WTS.setClickListener(new WordToSpan.ClickListener() {
	@Override
	public void onClick(String type, String text) {
		// type: "tag", "mail", "url" or "mention"
		Toast.makeText(getApplicationContext(), "Type: " + type + "\nText: " + text, Toast.LENGTH_LONG).show();
	}
});
```

Create Highlight
-----
![gif](http://i.giphy.com/3ohhwpbkD8NRGefEyY.gif)

```java
// settings
WTS.setBackgroundHIGHLIGHT(Color.YELLOW); // Default = Color.BLUE
WTS.setColorHIGHLIGHT(Color.RED); // Default = Color.WHITE

// create highlighter
String myText = "Any code and resources in the Android library anywhere love code.";
String keyWord = "any code";
TextView TV = (TextView)findViewById(R.id.txt);
WTS.setHighlight(myText, keyWord, TV);
```

MIT
---
