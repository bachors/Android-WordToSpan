package com.bachors.wordtospan;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
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
    private int colorPHONE = Color.BLUE;
    private int colorCUSTOM = Color.BLUE;
    private int colorHIGHLIGHT = Color.WHITE;
    private int backgroundHIGHLIGHT = Color.BLUE;
    private String regexCUSTOM = null;
    private boolean underlineTAG = false;
    private boolean underlineMENTION = false;
    private boolean underlineURL = false;
    private boolean underlineMAIL = false;
    private boolean underlinePHONE = false;
    private boolean underlineCUSTOM = false;
    private ClickListener clickListener;

    // custom
    public void setRegexCUSTOM(String regexCUSTOM){
        this.regexCUSTOM = regexCUSTOM;
    }

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

    public void setColorPHONE(int colorPHONE){
        this.colorPHONE = colorPHONE;
    }

    public void setColorCUSTOM(int colorCUSTOM){
        this.colorCUSTOM = colorCUSTOM;
    }

    public void setColorHIGHLIGHT(int colorHIGHLIGHT){
        this.colorHIGHLIGHT = colorHIGHLIGHT;
    }

    // background
    public void setBackgroundHIGHLIGHT(int backgroundHIGHLIGHT){
        this.backgroundHIGHLIGHT = backgroundHIGHLIGHT;
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

    public void setUnderlinePHONE(boolean underlinePHONE){
        this.underlinePHONE = underlinePHONE;
    }

    public void setUnderlineCUSTOM(boolean underlineCUSTOM){
        this.underlineCUSTOM = underlineCUSTOM;
    }

    // listener
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // create link
    public void setLink(String txt, View textView) {
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

        Matcher matcherPHONE = Pattern.compile("\\d{13}|\\d{12}|\\d{11}|\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}").matcher(txt);
        while(matcherPHONE.find()){
            int st = matcherPHONE.start();
            int en = st + matcherPHONE.group(0).length();
            ws.setSpan(new myClickableSpan(colorPHONE, underlinePHONE, "phone"), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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

    // create highlight
    public void setHighlight(String txt, String key, View textView) {
        Spannable ws = new SpannableString(txt);

        if(!key.isEmpty()) {
            Matcher matcherONE = Pattern.compile("(?i)" + key.trim()).matcher(txt);
            while (matcherONE.find()) {
                int st = matcherONE.start();
                int en = st + matcherONE.group(0).length();
                ws.setSpan(new ForegroundColorSpan(colorHIGHLIGHT), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ws.setSpan(new BackgroundColorSpan(backgroundHIGHLIGHT), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            for (String retval: key.split(" ")) {
                Matcher matcherALL = Pattern.compile("(?i)" + retval.trim()).matcher(txt);
                while (matcherALL.find()) {
                    int st = matcherALL.start();
                    int en = st + matcherALL.group(0).length();
                    ws.setSpan(new ForegroundColorSpan(colorHIGHLIGHT), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ws.setSpan(new BackgroundColorSpan(backgroundHIGHLIGHT), st, en, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        // set text
        TextView tv = (TextView) textView;
        tv.setText(ws);
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
