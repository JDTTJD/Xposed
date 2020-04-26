package com.tuoren.xposed;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;

/**
 * Create by JDT on 2020-04-26.
 */
public class XmlParser {

    /**
     * xml pull解析
     * @return
     */
    public static String getNativeUrl(InputStream inputStream) {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(inputStream, "UTF-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
