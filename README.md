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
    compile 'com.github.bachors:Android-WordToSpan:3.0'
}
```

Create Link
-----
![gif](http://i.giphy.com/3o7qiVzv4pGAbBaCUU.gif)

```java
String myText = "I know http://just.com/anu how to @whisper, And I #know just #how to cry,I know just @where to anu@find.com the answers";
TextView tv = (TextView)findViewById(R.id.tx);

WordToSpan link = new WordToSpan();
link.setColorTAG(Color.GREEN)
	.setColorURL(Color.MAGENTA)
	.setColorPHONE(Color.RED)
	.setColorMAIL(getResources().getColor(R.color.colorPrimary))
	.setColorMENTION(getResources().getColor(R.color.colorAccent))
	.setUnderlineURL(true)
	.setLink(myText)
	.into(tv)
	.setClickListener(new WordToSpan.ClickListener() {
		@Override
		public void onClick(String type, String text) {
			// type: "tag", "mail", "url", "phone", "mention" or "custom"
			Toast.makeText(getApplication(), "Type: " + type + "\nText: " + text, Toast.LENGTH_LONG).show();
		}
	});
```

Create Highlight
-----
![gif](http://i.giphy.com/3ohhwpbkD8NRGefEyY.gif)

```java
String myText = "Any code and resources in the Android library anywhere love code.";
String keyWord = "any code";
TextView tv = (TextView)findViewById(R.id.tx);

WordToSpan highlight = new WordToSpan();
highlight.setBackgroundHIGHLIGHT(Color.YELLOW)
	.setColorHIGHLIGHT(Color.RED)
	.setHighlight(myText, keyWord)
	.into(tv);
```

MIT
---
