# Android-WordToSpan
Small &amp; simple library to create a link url, mail, mention and tag in textView.

![gif](http://i.giphy.com/3o7qiVzv4pGAbBaCUU.gif)

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
    compile 'com.github.bachors:Android-WordToSpan:1.0'
}
```

Usage
-----
```java
...

// init
WordToSpan WTS = new WordToSpan();

// set color link. Default = Color.BLUE
WTS.setColorTAG(Color.GREEN);
WTS.setColorURL(Color.MAGENTA);
WTS.setColorMAIL(getResources().getColor(R.color.colorPrimary));
WTS.setColorMENTION(getResources().getColor(R.color.colorAccent));

/* add custom
WTS.setRegexCUSTOM("([0-9]+-[0-9]+)");
WTS.setColorCUSTOM(Color.YELLOW);
*/

// set underline link. Default = false
// WTS.setUnderlineTAG(true);
WTS.setUnderlineURL(true);

// convert string to spannable & set text
String myText = "I know http://just.com/anu how to @whisper, And I #know just #how to cry,I know just @where to anu@find.com the answers";
TextView TV = (TextView)findViewById(R.id.txt);
// WTS.setText(String, TextView);
WTS.setText(myText, TV);

/* more string & textView
	.....
*/

// click listener
WTS.setClickListener(new WordToSpan.ClickListener() {
	@Override
	public void onClick(String type, String text) {
		// type: "tag", "mail", "url" or "mention"
		Toast.makeText(getApplicationContext(), "Type: " + type + "\nText: " + text, Toast.LENGTH_LONG).show();
	}
});
```

MIT
---
