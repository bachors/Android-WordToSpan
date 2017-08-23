package com.bachors.wordtospan;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bachors on 8/23/2017.
 * https://github.com/bachors/Android-WordToSpan
 */

public class WordToSpan {

    private int colorTAG = Color.BLUE;
    private int colorMENTION = Color.BLUE;
    private int colorURL = Color.BLUE;
    private int colorMAIL = Color.BLUE;
    private int colorCUSTOM = Color.BLUE;
    private String regexCUSTOM = null;
    private boolean underlineTAG = false;
    private boolean underlineMENTION = false;
    private boolean underlineURL = false;
    private boolean underlineMAIL = false;
    private boolean underlineCUSTOM = false;
    private ClickListener clickListener;

    // colors
    public void setColorTAG(int colorTAG){
        this.colorTAG = colorTAG;
    }

    public void setColorMENTION(int colorMENTION){
        this.colorMENTION = colorMENTION;
    }

    public void setColorURL(int colorURL){
        this.colorURL = colorURL;
    }

    public void setColorMAIL(int colorMAIL){
        this.colorMAIL = colorMAIL;
    }

    // custom
    public void setColorCUSTOM(int colorCUSTOM){
        this.colorCUSTOM = colorCUSTOM;
    }

    public void setRegexCUSTOM(String regexCUSTOM){
        this.regexCUSTOM = regexCUSTOM;
    }

    // underline
    public void setUnderlineTAG(boolean underlineTAG){
        this.underlineTAG = underlineTAG;
    }

    public void setUnderlineMENTION(boolean underlineMENTION){
        this.underlineMENTION = underlineMENTION;
    }

    public void setUnderlineURL(boolean underlineURL){
        this.underlineURL = underlineURL;
    }

    public void setUnderlineMAIL(boolean underlineMAIL){
        this.underlineMAIL = underlineMAIL;
    }

    public void setUnderlineCUSTOM(boolean underlineCUSTOM){
        this.underlineCUSTOM = underlineCUSTOM;
    }

    // listener
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // converter
    public void setText(String txt, View textView) {
        Spannable ws = new SpannableString(txt);

        Matcher matcherTAG = Pattern.compile("(^|\\s+)#(\\w+)").matcher(txt);
        while(matcherTAG.find()){
            int st = matcherTAG.start();
            int en = st + matcherTAG.group(0).length();
            ws.setSpan(new myClickableSpan(colorTAG, underlineTAG, "tag"), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        Matcher matcherMENTION = Pattern.compile("(^|\\s+)@(\\w+)").matcher(txt);
        while(matcherMENTION.find()){
            int st = matcherMENTION.start();
            int en = st + matcherMENTION.group(0).length();
            ws.setSpan(new myClickableSpan(colorMENTION, underlineMENTION, "mention"), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        Matcher matcherURL = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]").matcher(txt);
        while(matcherURL.find()){
            int st = matcherURL.start();
            int en = st + matcherURL.group(0).length();
            ws.setSpan(new myClickableSpan(colorURL, underlineURL, "url"), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        Matcher matcherMAIL = Pattern.compile("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9._-]+)").matcher(txt);
        while(matcherMAIL.find()){
            int st = matcherMAIL.start();
            int en = st + matcherMAIL.group(0).length();
            ws.setSpan(new myClickableSpan(colorMAIL, underlineMAIL, "mail"), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if(regexCUSTOM != null && !regexCUSTOM.isEmpty()) {
            Matcher matcherCUSTOM = Pattern.compile(regexCUSTOM).matcher(txt);
            while (matcherCUSTOM.find()) {
                int st = matcherCUSTOM.start();
                int en = st + matcherCUSTOM.group(0).length();
                ws.setSpan(new myClickableSpan(colorCUSTOM, underlineCUSTOM, "custom"), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        // set text
        TextView tv = (TextView) textView;
        tv.setText(ws);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(Color.TRANSPARENT);
    }

    // interface
    public interface ClickListener {
        void onClick(String type, String text);
    }

    // click
    private class myClickableSpan extends ClickableSpan {
        int color;
        String type;
        boolean underline;

        myClickableSpan(int color, boolean underline, String type){
            this.color = color;
            this.underline = underline;
            this.type = type;
        }

        @Override
        public void onClick(View textView) {
            TextView tv = (TextView) textView;
            Spanned s = (Spanned) tv.getText();
            int start = s.getSpanStart(this);
            int end = s.getSpanEnd(this);
            clickListener.onClick(type, s.subSequence(start, end).toString().trim());
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(color);
            ds.setUnderlineText(underline);
        }
    }

}