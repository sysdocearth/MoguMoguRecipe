/*
 * Copyright 2004-2012 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
// Add commons-lang3-3.1

package com.sysdoccode.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * {@link String}用のユーティリティクラスです。
 * 
 * @author higa
 * 
 */
public class StringUtils {

    /**
     * 空の文字列の配列です。
     */
    public static final String[] EMPTY_STRINGS = new String[0];
    
    /**
     * The empty String {@code ""}.
     */
    public static final String EMPTY = "";
    
    /**
     * 全角スペースです。
     */
    public static final String ZENKAKUSPACE = String.valueOf('\u3000');

    /**
     * 
     */
    protected StringUtils() {
    }

    /**
     * 文字列が<code>null</code>または空文字列なら<code>true</code>を返します。
     *      * @param text
     *            文字列
     * @return 文字列が<code>null</code>または空文字列なら<code>true</code>
     */
    public static final boolean isEmpty(final String text) {
        return text == null || text.length() == 0;
    }

    /**
     * 文字列が<code>null</code>でも空文字列でもなければ<code>true</code>を返します。
     * 
     * @param text
     *            文字列
     * @return 文字列が<code>null</code>でも空文字列でもなければ<code>true</code>
     * @since 2.4.33
     */
    public static final boolean isNotEmpty(final String text) {
        return !isEmpty(text);
    }

    /**
     * 文字列を置き換えます。
     * 
     * @param text
     *            テキスト
     * @param fromText
     *            置き換え対象のテキスト
     * @param toText
     *            置き換えるテキスト
     * @return 結果
     */
    public static final String replace(final String text,
            final String fromText, final String toText) {

        if (text == null || fromText == null || toText == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(100);
        int pos = 0;
        int pos2 = 0;
        while (true) {
            pos = text.indexOf(fromText, pos2);
            if (pos == 0) {
                buf.append(toText);
                pos2 = fromText.length();
            } else if (pos > 0) {
                buf.append(text.substring(pos2, pos));
                buf.append(toText);
                pos2 = pos + fromText.length();
            } else {
                buf.append(text.substring(pos2));
                break;
            }
        }
        return buf.toString();
    }

    /**
     * 文字列を分割します。
     * 
     * @param str
     *            文字列
     * @param delim
     *            分割するためのデリミタ
     * @return 分割された文字列の配列
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String[] split(final String str, final String delim) {
        if (isEmpty(str)) {
            return EMPTY_STRINGS;
        }
        List list = new ArrayList();
        StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreElements()) {
            list.add(st.nextElement());
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 左側の空白を削ります。
     * 
     * @param text
     *            テキスト
     * @return 結果の文字列
     */
    public static final String ltrim(final String text) {
        return ltrim(text, null);
    }

    /**
     * 左側の指定した文字列を削ります。
     * 
     * @param text
     *            テキスト
     * @param trimText
     *            削るテキスト
     * @return 結果の文字列
     */
    public static final String ltrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        int pos = 0;
        for (; pos < text.length(); pos++) {
            if (trimText.indexOf(text.charAt(pos)) < 0) {
                break;
            }
        }
        return text.substring(pos);
    }

    /**
     * 右側の空白を削ります。
     * 
     * @param text
     *            テキスト
     * @return 結果の文字列
     */
    public static final String rtrim(final String text) {
        return rtrim(text, null);
    }

    /**
     * 右側の指定した文字列を削ります。
     * 
     * @param text
     *            テキスト
     * @param trimText
     *            削る文字列
     * @return 結果の文字列
     */
    public static final String rtrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        int pos = text.length() - 1;
        for (; pos >= 0; pos--) {
            if (trimText.indexOf(text.charAt(pos)) < 0) {
                break;
            }
        }
        return text.substring(0, pos + 1);
    }

    /**
     * サフィックスを削ります。
     * 
     * @param text
     *            テキスト
     * @param suffix
     *            サフィックス
     * @return 結果の文字列
     */
    public static final String trimSuffix(final String text, final String suffix) {
        if (text == null) {
            return null;
        }
        if (suffix == null) {
            return text;
        }
        if (text.endsWith(suffix)) {
            return text.substring(0, text.length() - suffix.length());
        }
        return text;
    }

    /**
     * プレフィックスを削ります。
     * 
     * @param text
     *            テキスト
     * @param prefix
     *            プレフィックス
     * @return 結果の文字列
     */
    public static final String trimPrefix(final String text, final String prefix) {
        if (text == null) {
            return null;
        }
        if (prefix == null) {
            return text;
        }
        if (text.startsWith(prefix)) {
            return text.substring(prefix.length());
        }
        return text;
    }

    /**
     * JavaBeansの仕様にしたがってデキャピタライズを行ないます。大文字が2つ以上続く場合は、小文字にならないので注意してください。
     * 
     * @param name
     *            名前
     * @return 結果の文字列
     */
    public static String decapitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        char chars[] = name.toCharArray();
        if (chars.length >= 2 && Character.isUpperCase(chars[0])
                && Character.isUpperCase(chars[1])) {
            return name;
        }
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    /**
     * JavaBeansの仕様にしたがってキャピタライズを行ないます。大文字が2つ以上続く場合は、大文字にならないので注意してください。
     * 
     * @param name
     *            名前
     * @return 結果の文字列
     */
    public static String capitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        char chars[] = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    /**
     * ケースインセンシティブで特定の文字列で開始されているかどうかを返します。
     * 
     * @param text
     *            テキスト
     * @param fragment
     *            特定の文字列
     * @return ケースインセンシティブで特定の文字列で開始されているかどうか
     * @see #startsWithIgnoreCase(String, String)
     * @deprecated
     */
    public static boolean startsWith(final String text, final String fragment) {
        return startsWithIgnoreCase(text, fragment);
    }

    /**
     * ブランクかどうか返します。
     * 
     * @param str
     *            文字列
     * @return ブランクかどうか
     */
    public static boolean isBlank(final String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * ブランクではないかどうか返します。
     * 
     * @param str
     *            文字列
     * @return ブランクではないかどうか
     * @see #isBlank(String)
     */
    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    /**
     * charを含んでいるかどうか返します。
     * 
     * @param str
     *            文字列
     * @param ch
     *            char
     * @return charを含んでいるかどうか
     */
    public static boolean contains(final String str, final char ch) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(ch) >= 0;
    }

    /**
     * 文字列を含んでいるかどうか返します。
     * 
     * @param s1
     *            文字列
     * @param s2
     *            比較する対象となる文字列
     * @return 文字列を含んでいるかどうか
     */
    public static boolean contains(final String s1, final String s2) {
        if (isEmpty(s1)) {
            return false;
        }
        return s1.indexOf(s2) >= 0;
    }

    /**
     * 文字列同士が等しいかどうか返します。どちらもnullの場合は、<code>true</code>を返します。
     * 
     * @param target1
     *            文字列1
     * @param target2
     *            文字列2
     * @return 文字列同士が等しいかどうか
     */
    public static boolean equals(final String target1, final String target2) {
        return (target1 == null) ? (target2 == null) : target1.equals(target2);
    }

    /**
     * ケースインセンシティブで文字列同士が等しいかどうか返します。どちらもnullの場合は、<code>true</code>を返します。
     * 
     * @param target1
     *            文字列1
     * @param target2
     *            文字列2
     * @return ケースインセンシティブで文字列同士が等しいか
     */
    public static boolean equalsIgnoreCase(final String target1,
            final String target2) {
        return (target1 == null) ? (target2 == null) : target1
                .equalsIgnoreCase(target2);
    }

    /**
     * ケースインセンシティブで特定の文字で終わっているのかどうかを返します。
     * 
     * @param target1
     *            テキスト
     * @param target2
     *            比較する文字列
     * @return ケースインセンシティブで特定の文字で終わっているのかどうか
     */
    public static boolean endsWithIgnoreCase(final String target1,
            final String target2) {
        if (target1 == null || target2 == null) {
            return false;
        }
        int length1 = target1.length();
        int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        String s1 = target1.substring(length1 - length2);
        return s1.equalsIgnoreCase(target2);
    }

    /**
     * ケースインセンシティブで特定の文字ではじまっているのかどうかを返します。
     * 
     * @param target1
     *            テキスト
     * @param target2
     *            比較する文字列
     * @return ケースインセンシティブで特定の文字ではじまっているのかどうか
     */
    public static boolean startsWithIgnoreCase(final String target1,
            final String target2) {
        if (target1 == null || target2 == null) {
            return false;
        }
        int length1 = target1.length();
        int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        String s1 = target1.substring(0, target2.length());
        return s1.equalsIgnoreCase(target2);
    }

    /**
     * 文字列の最後から指定した文字列で始まっている部分より手前を返します。
     * 
     * @param str
     *            文字列
     * @param separator
     *            セパレータ
     * @return 結果の文字列
     */
    public static String substringFromLast(final String str,
            final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * 文字列の最後から指定した文字列で始まっている部分より後ろを返します。
     * 
     * @param str
     *            文字列
     * @param separator
     *            セパレータ
     * @return 結果の文字列
     */
    public static String substringToLast(final String str,
            final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(pos + 1, str.length());
    }

    /**
     * 16進数の文字列に変換します。
     * 
     * @param bytes
     *            バイトの配列
     * @return 16進数の文字列
     */
    public static String toHex(final byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            appendHex(sb, bytes[i]);
        }
        return sb.toString();
    }

    /**
     * 16進数の文字列に変換します。
     * 
     * @param i
     *            int
     * @return 16進数の文字列
     */
    public static String toHex(final int i) {
        StringBuffer buf = new StringBuffer();
        appendHex(buf, i);
        return buf.toString();
    }

    /**
     * 文字列に、数値を16進数に変換した文字列を追加します。
     * 
     * @param buf
     *            追加先の文字列
     * @param i
     *            数値
     */
    public static void appendHex(final StringBuffer buf, final byte i) {
        buf.append(Character.forDigit((i & 0xf0) >> 4, 16));
        buf.append(Character.forDigit((i & 0x0f), 16));
    }

    /**
     * 文字列に、数値を16進数に変換した文字列を追加します。
     * 
     * @param buf
     *            追加先の文字列
     * @param i
     *            数値
     */
    public static void appendHex(final StringBuffer buf, final int i) {
        buf.append(Integer.toHexString((i >> 24) & 0xff));
        buf.append(Integer.toHexString((i >> 16) & 0xff));
        buf.append(Integer.toHexString((i >> 8) & 0xff));
        buf.append(Integer.toHexString(i & 0xff));
    }

    /**
     * _記法をキャメル記法に変換します。
     * 
     * @param s
     *            テキスト
     * @return 結果の文字列
     */
    public static String camelize(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        String[] array = StringUtils.split(s, "_");
        if (array.length == 1) {
            return StringUtils.capitalize(s);
        }
        StringBuffer buf = new StringBuffer(40);
        for (int i = 0; i < array.length; ++i) {
            buf.append(StringUtils.capitalize(array[i]));
        }
        return buf.toString();
    }

    /**
     * キャメル記法を_記法に変換します。
     * 
     * @param s
     *            テキスト
     * @return 結果の文字列
     */
    public static String decamelize(final String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 1) {
            return s.toUpperCase();
        }
        StringBuffer buf = new StringBuffer(40);
        int pos = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (Character.isUpperCase(s.charAt(i))) {
                if (buf.length() != 0) {
                    buf.append('_');
                }
                buf.append(s.substring(pos, i).toUpperCase());
                pos = i;
            }
        }
        if (buf.length() != 0) {
            buf.append('_');
        }
        buf.append(s.substring(pos, s.length()).toUpperCase());
        return buf.toString();
    }

    /**
     * 文字列が数値のみで構成されているかどうかを返します。
     * 
     * @param s
     *            文字列
     * @return 数値のみで構成されている場合、<code>true</code>
     */
    public static boolean isNumber(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int size = s.length();
        for (int i = 0; i < size; i++) {
            char chr = s.charAt(i);
            if (chr < '0' || '9' < chr) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * 対象の文字列が<code>null</code>または空文字列なら<code>defaultStr</code>を返します。
     * 
     * @param targetStr　
     *                  対象文字列
     * @param defaultStr 
     *                  変換文字列
     *                  
     * @return　 
     * 文字列が<code>null</code>または空文字列なら<code>defaultStr</code>
     * <br>そうでない場合は対象文字列をそのまま返します。
     * 
     * @author Y.Takeuchi
     */
    public static String defaultIfEmpty(final String targetStr, final String defaultStr){
        if(isEmpty(targetStr))
        {
            return defaultStr;
        }
        return targetStr;        
    }

    // Defaults
    //-----------------------------------------------------------------------
    /**
     * <p>Returns either the passed in String,
     * or if the String is {@code null}, an empty String ("").</p>
     *
     * <pre>
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * </pre>
     *
     * @see ObjectUtils#toString(Object)
     * @see String#valueOf(Object)
     * @param str  the String to check, may be null
     * @return the passed in String, or the empty String if it
     *  was {@code null}
     */
    public static String defaultString(String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * <p>Returns either the passed in String, or if the String is
     * {@code null}, the value of {@code defaultStr}.</p>
     *
     * <pre>
     * StringUtils.defaultString(null, "NULL")  = "NULL"
     * StringUtils.defaultString("", "NULL")    = ""
     * StringUtils.defaultString("bat", "NULL") = "bat"
     * </pre>
     *
     * @see ObjectUtils#toString(Object,String)
     * @see String#valueOf(Object)
     * @param str  the String to check, may be null
     * @param defaultStr  the default String to return
     *  if the input is {@code null}, may be null
     * @return the passed in String, or the default if it was {@code null}
     */
    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }
    
    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start/end {@code n}
     * characters from the end of the String.</p>
     *
     * <p>The returned substring starts with the character in the {@code start}
     * position and ends before the {@code end} position. All position counting is
     * zero-based -- i.e., to start at the beginning of the string use
     * {@code start = 0}. Negative start and end positions can be used to
     * specify offsets relative to the end of the String.</p>
     *
     * <p>If {@code start} is not strictly to the left of {@code end}, ""
     * is returned.</p>
     *
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @param end  the position to end at (exclusive), negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position to end position,
     *  {@code null} if null String input
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }
    
    // Character Tests
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if the CharSequence contains only Unicode letters.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     *
     * <pre>
     * StringUtils.isAlpha(null)   = false
     * StringUtils.isAlpha("")     = false
     * StringUtils.isAlpha("  ")   = false
     * StringUtils.isAlpha("abc")  = true
     * StringUtils.isAlpha("ab2c") = false
     * StringUtils.isAlpha("ab-c") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if only contains letters, and is non-null
     * @since 3.0 Changed signature from isAlpha(String) to isAlpha(CharSequence)
     * @since 3.0 Changed "" to return false and not true
     */
    public static boolean isAlpha(CharSequence cs) {
        if (cs == null || cs.length() == 0) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Gets a CharSequence length or {@code 0} if the CharSequence is
     * {@code null}.
     *
     * @param cs
     *            a CharSequence or {@code null}
     * @return CharSequence length or {@code 0} if the CharSequence is
     *         {@code null}.
     * @since 2.4
     * @since 3.0 Changed signature from length(String) to length(CharSequence)
     */
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
    
    /**
     * CSVレコードをLinkedListに格納して返します。
     * @param src　対象文字列
     * @return　フィールドごとにに分割したものが格納されています。
     * 
     * @author Y.Takeuchi
     */
    public static LinkedList<String> splitRecord (String src)
    {
        LinkedList<String> dest = new LinkedList<String>();
        String[]    columns = src.split(",", -1);//2014-01-31 Y.Takeuchi Edit レコード末尾空白項目取込
        int       maxlen = columns.length;
        int       startPos, endPos, columnlen;
        StringBuffer buff = new StringBuffer(1024);
        String    column;
        boolean isInString, isEscaped;
        
        for (int index = 0; index < maxlen; index++) {
            column = columns[index];
            if ((endPos = column.indexOf("\"")) < 0) {
                dest.addLast(column);
            }
            else {
                isInString = (endPos == 0);
                isEscaped = false;
                columnlen = column.length();
                buff.setLength(0);
                startPos = (isInString)? 1: 0;
                while (startPos < columnlen) {
                    if (0 <= (endPos = column.indexOf("\"", startPos))) {
                        buff.append((startPos < endPos)?
                                    column.substring(startPos, endPos): isEscaped? "\"": "");
                        isEscaped = !isEscaped;
                        isInString = !isInString;
                        startPos = ++endPos;
                    }
                    else {
                        buff.append(column.substring(startPos));
                        if (isInString && index < maxlen - 1) {
                            column = columns[++index];
                            columnlen = column.length();
                            buff.append(",");
                            startPos = 0;
                        }
                        else {
                            break;
                        }
                    }
                }
                dest.addLast(buff.toString());
            }
        }
        return dest;
    }
    

    /**
     * Returns the approximate display width of the string, measured in units of
     * ascii characters.
     *
     * @see displayWidth(char)
     */
    public static int displayWidth(String s) {
      int width = 0;
      int len = s.length();
      for (int i = 0; i < len; ++i) {
        width += displayWidth(s.charAt(i));
      }
      return width;
    }

    /**
     * Returns the approximate display width of the character, measured
     * in units of ascii characters.
     *
     * This method should err on the side of caution. By default, characters
     * are assumed to have width 2; this covers CJK ideographs, various
     * symbols and miscellaneous weird scripts. Given below are some Unicode
     * ranges for which it seems safe to assume that no character is
     * substantially wider than an ascii character:
     *   - Latin, extended Latin, even more extended Latin.
     *   - Greek, extended Greek, Cyrillic.
     *   - Some symbols (including currency symbols) and punctuation.
     *   - Half-width Katakana and Hangul.
     *   - Hebrew
     *   - Thai
     * Characters in these ranges are given a width of 1.
     *
     * IMPORTANT: this function has an analog in strutil.cc named
     * UnicodeCharWidth, which needs to be updated if you change the
     * implementation here.
     */
    public static int displayWidth(char ch) {
      if (ch <= '\u04f9' ||
          ch == '\u05be' ||
          (ch >= '\u05d0' && ch <= '\u05ea') ||
          ch == '\u05F3' ||
          ch == '\u05f4' ||
          (ch >= '\u0e00' && ch <= '\u0e7f') ||
          (ch >= '\u1e00' && ch <= '\u20af') ||
          (ch >= '\u2100' && ch <= '\u213a') ||
          (ch >= '\uff61' && ch <= '\uffdc')) {
        return 1;
      }
      return 2;
    }

}
