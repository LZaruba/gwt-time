/*
 *  Copyright 2001-2006 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time;


import java.util.Date;

import org.joda.time.gwt.JodaGwtTestCase;
import static org.joda.time.gwt.TestConstants.*;
//import junit.framework.TestSuite;

import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.UnsupportedDateTimeField;
import org.joda.time.field.UnsupportedDurationField;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.gwt.i18n.client.LocaleInfo;

/**
 * This class is a JUnit test for MutableDateTime.
 *
 * @author Stephen Colebourne
 */
public class TestMutableDateTime_Basics extends JodaGwtTestCase {
    // Test in 2002/03 as time zones are more well known
    // (before the late 90's they were all over the place)

    // Removed for GWT private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    // Removed for GWT private static final DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
    
    long y2002days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365;
    long y2003days = 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 
                     366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 
                     365 + 365 + 366 + 365 + 365 + 365 + 366 + 365 + 365 + 365 +
                     366 + 365 + 365;
    
    // 2002-06-09
    private long TEST_TIME_NOW =
            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
            
    // 2002-04-05
    private long TEST_TIME1 =
            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 12L * DateTimeConstants.MILLIS_PER_HOUR
            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
        
    // 2003-05-06
    private long TEST_TIME2 =
            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
            + 14L * DateTimeConstants.MILLIS_PER_HOUR
            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
    
    private DateTimeZone originalDateTimeZone = null;

    /* Removed for GWT public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } */

    /* Removed for GWT public static TestSuite suite() {
        return new TestSuite(TestMutableDateTime_Basics.class);
    } */

    /* Removed for GWT public TestMutableDateTime_Basics(String name) {
        super(name);
    } */

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW);
        originalDateTimeZone = DateTimeZone.getDefault();
        /* //BEGIN GWT IGNORE
        originalTimeZone = TimeZone.getDefault();
        originalLocale = Locale.getDefault();
        //END GWT IGNORE */
        DateTimeZone.setDefault(LONDON);
        /* //BEGIN GWT IGNORE
        //TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));
        //Locale.setDefault(Locale.UK);
        TimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo").toTimeZone());
        Locale.setDefault(Locale.JAPAN);
        //END GWT IGNORE */
    }

    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(originalDateTimeZone);
        /* //BEGIN GWT IGNORE
        TimeZone.setDefault(originalTimeZone);
        Locale.setDefault(originalLocale);
        //END GWT IGNORE */
        originalDateTimeZone = null;
    }

    //-----------------------------------------------------------------------
    public void testTest() {
        assertEquals("2002-06-09T00:00:00.000Z", new Instant(TEST_TIME_NOW).toString());
        assertEquals("2002-04-05T12:24:00.000Z", new Instant(TEST_TIME1).toString());
        assertEquals("2003-05-06T14:28:00.000Z", new Instant(TEST_TIME2).toString());
    }

    //-----------------------------------------------------------------------
    public void testGet_DateTimeField() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1, test.get(ISOChronology.getInstance().era()));
        assertEquals(20, test.get(ISOChronology.getInstance().centuryOfEra()));
        assertEquals(2, test.get(ISOChronology.getInstance().yearOfCentury()));
        assertEquals(2002, test.get(ISOChronology.getInstance().yearOfEra()));
        assertEquals(2002, test.get(ISOChronology.getInstance().year()));
        assertEquals(6, test.get(ISOChronology.getInstance().monthOfYear()));
        assertEquals(9, test.get(ISOChronology.getInstance().dayOfMonth()));
        assertEquals(2002, test.get(ISOChronology.getInstance().weekyear()));
        assertEquals(23, test.get(ISOChronology.getInstance().weekOfWeekyear()));
        assertEquals(7, test.get(ISOChronology.getInstance().dayOfWeek()));
        assertEquals(160, test.get(ISOChronology.getInstance().dayOfYear()));
        assertEquals(0, test.get(ISOChronology.getInstance().halfdayOfDay()));
        assertEquals(1, test.get(ISOChronology.getInstance().hourOfHalfday()));
        assertEquals(1, test.get(ISOChronology.getInstance().clockhourOfDay()));
        assertEquals(1, test.get(ISOChronology.getInstance().clockhourOfHalfday()));
        assertEquals(1, test.get(ISOChronology.getInstance().hourOfDay()));
        assertEquals(0, test.get(ISOChronology.getInstance().minuteOfHour()));
        assertEquals(60, test.get(ISOChronology.getInstance().minuteOfDay()));
        assertEquals(0, test.get(ISOChronology.getInstance().secondOfMinute()));
        assertEquals(60 * 60, test.get(ISOChronology.getInstance().secondOfDay()));
        assertEquals(0, test.get(ISOChronology.getInstance().millisOfSecond()));
        assertEquals(60 * 60 * 1000, test.get(ISOChronology.getInstance().millisOfDay()));
        try {
            test.get((DateTimeField) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testGet_DateTimeFieldType() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(1, test.get(DateTimeFieldType.era()));
        assertEquals(20, test.get(DateTimeFieldType.centuryOfEra()));
        assertEquals(2, test.get(DateTimeFieldType.yearOfCentury()));
        assertEquals(2002, test.get(DateTimeFieldType.yearOfEra()));
        assertEquals(2002, test.get(DateTimeFieldType.year()));
        assertEquals(6, test.get(DateTimeFieldType.monthOfYear()));
        assertEquals(9, test.get(DateTimeFieldType.dayOfMonth()));
        assertEquals(2002, test.get(DateTimeFieldType.weekyear()));
        assertEquals(23, test.get(DateTimeFieldType.weekOfWeekyear()));
        assertEquals(7, test.get(DateTimeFieldType.dayOfWeek()));
        assertEquals(160, test.get(DateTimeFieldType.dayOfYear()));
        assertEquals(0, test.get(DateTimeFieldType.halfdayOfDay()));
        assertEquals(1, test.get(DateTimeFieldType.hourOfHalfday()));
        assertEquals(1, test.get(DateTimeFieldType.clockhourOfDay()));
        assertEquals(1, test.get(DateTimeFieldType.clockhourOfHalfday()));
        assertEquals(1, test.get(DateTimeFieldType.hourOfDay()));
        assertEquals(0, test.get(DateTimeFieldType.minuteOfHour()));
        assertEquals(60, test.get(DateTimeFieldType.minuteOfDay()));
        assertEquals(0, test.get(DateTimeFieldType.secondOfMinute()));
        assertEquals(60 * 60, test.get(DateTimeFieldType.secondOfDay()));
        assertEquals(0, test.get(DateTimeFieldType.millisOfSecond()));
        assertEquals(60 * 60 * 1000, test.get(DateTimeFieldType.millisOfDay()));
        try {
            test.get((DateTimeFieldType) null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testGetMethods() {
        MutableDateTime test = new MutableDateTime();
        
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(LONDON, test.getZone());
        assertEquals(TEST_TIME_NOW, test.getMillis());
        
        assertEquals(1, test.getEra());
        assertEquals(20, test.getCenturyOfEra());
        assertEquals(2, test.getYearOfCentury());
        assertEquals(2002, test.getYearOfEra());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
        assertEquals(2002, test.getWeekyear());
        assertEquals(23, test.getWeekOfWeekyear());
        assertEquals(7, test.getDayOfWeek());
        assertEquals(160, test.getDayOfYear());
        assertEquals(1, test.getHourOfDay());
        assertEquals(0, test.getMinuteOfHour());
        assertEquals(60, test.getMinuteOfDay());
        assertEquals(0, test.getSecondOfMinute());
        assertEquals(60 * 60, test.getSecondOfDay());
        assertEquals(0, test.getMillisOfSecond());
        assertEquals(60 * 60 * 1000, test.getMillisOfDay());
    }

    public void testEqualsHashCode() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test2 = new MutableDateTime(TEST_TIME1);
        assertEquals(true, test1.equals(test2));
        assertEquals(true, test2.equals(test1));
        assertEquals(true, test1.equals(test1));
        assertEquals(true, test2.equals(test2));
        assertEquals(true, test1.hashCode() == test2.hashCode());
        assertEquals(true, test1.hashCode() == test1.hashCode());
        assertEquals(true, test2.hashCode() == test2.hashCode());
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.equals(test3));
        assertEquals(false, test2.equals(test3));
        assertEquals(false, test3.equals(test1));
        assertEquals(false, test3.equals(test2));
        assertEquals(false, test1.hashCode() == test3.hashCode());
        assertEquals(false, test2.hashCode() == test3.hashCode());
        
        DateTime test4 = new DateTime(TEST_TIME2);
        assertEquals(true, test4.equals(test3));
        assertEquals(true, test3.equals(test4));
        assertEquals(false, test4.equals(test1));
        assertEquals(false, test1.equals(test4));
        assertEquals(true, test3.hashCode() == test4.hashCode());
        assertEquals(false, test1.hashCode() == test4.hashCode());
        
        MutableDateTime test5 = new MutableDateTime(TEST_TIME2);
        test5.setRounding(ISOChronology.getInstance().millisOfSecond());
        assertEquals(true, test5.equals(test3));
        assertEquals(true, test5.equals(test4));
        assertEquals(true, test3.equals(test5));
        assertEquals(true, test4.equals(test5));
        assertEquals(true, test3.hashCode() == test5.hashCode());
        assertEquals(true, test4.hashCode() == test5.hashCode());
        
        assertEquals(false, test1.equals("Hello"));
        assertEquals(true, test1.equals(new MockInstant()));
        assertEquals(false, test1.equals(new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance())));
        assertEquals(true, new MutableDateTime(TEST_TIME1, new MockEqualsChronology()).equals(new MutableDateTime(TEST_TIME1, new MockEqualsChronology())));
        assertEquals(false, new MutableDateTime(TEST_TIME1, new MockEqualsChronology()).equals(new MutableDateTime(TEST_TIME1, ISOChronology.getInstance())));
    }
    
    class MockInstant extends AbstractInstant {
        public String toString() {
            return null;
        }
        public long getMillis() {
            return TEST_TIME1;
        }
        public Chronology getChronology() {
            return ISOChronology.getInstance();
        }
    }

    class MockEqualsChronology extends BaseChronology {
        /**
		 * 
		 */
		private static final long serialVersionUID = -6031445395528016284L;
		public boolean equals(Object obj) {
            return obj instanceof MockEqualsChronology;
        }
        public DateTimeZone getZone() {
            return null;
        }
        public Chronology withUTC() {
            return this;
        }
        public Chronology withZone(DateTimeZone zone) {
            return this;
        }
        public String toString() {
            return "";
        }
    }

    public void testCompareTo() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(0, test1.compareTo(test1a));
        assertEquals(0, test1a.compareTo(test1));
        assertEquals(0, test1.compareTo(test1));
        assertEquals(0, test1a.compareTo(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(-1, test1.compareTo(test2));
        assertEquals(+1, test2.compareTo(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(-1, test1.compareTo(test3));
        assertEquals(+1, test3.compareTo(test1));
        assertEquals(0, test3.compareTo(test2));
        
        assertEquals(+1, test2.compareTo(new MockInstant()));
        assertEquals(0, test1.compareTo(new MockInstant()));
    }
    
    public void testIsEqual() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(true, test1.isEqual(test1a));
        assertEquals(true, test1a.isEqual(test1));
        assertEquals(true, test1.isEqual(test1));
        assertEquals(true, test1a.isEqual(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.isEqual(test2));
        assertEquals(false, test2.isEqual(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isEqual(test3));
        assertEquals(false, test3.isEqual(test1));
        assertEquals(true, test3.isEqual(test2));
        
        assertEquals(false, test2.isEqual(new MockInstant()));
        assertEquals(true, test1.isEqual(new MockInstant()));
        
        assertEquals(false, new MutableDateTime(TEST_TIME_NOW + 1).isEqual(null));
        assertEquals(true, new MutableDateTime(TEST_TIME_NOW).isEqual(null));
        assertEquals(false, new MutableDateTime(TEST_TIME_NOW - 1).isEqual(null));
    }
    
    public void testIsBefore() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false, test1.isBefore(test1a));
        assertEquals(false, test1a.isBefore(test1));
        assertEquals(false, test1.isBefore(test1));
        assertEquals(false, test1a.isBefore(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(true, test1.isBefore(test2));
        assertEquals(false, test2.isBefore(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(true, test1.isBefore(test3));
        assertEquals(false, test3.isBefore(test1));
        assertEquals(false, test3.isBefore(test2));
        
        assertEquals(false, test2.isBefore(new MockInstant()));
        assertEquals(false, test1.isBefore(new MockInstant()));
        
        assertEquals(false, new MutableDateTime(TEST_TIME_NOW + 1).isBefore(null));
        assertEquals(false, new MutableDateTime(TEST_TIME_NOW).isBefore(null));
        assertEquals(true, new MutableDateTime(TEST_TIME_NOW - 1).isBefore(null));
    }
    
    public void testIsAfter() {
        MutableDateTime test1 = new MutableDateTime(TEST_TIME1);
        MutableDateTime test1a = new MutableDateTime(TEST_TIME1);
        assertEquals(false, test1.isAfter(test1a));
        assertEquals(false, test1a.isAfter(test1));
        assertEquals(false, test1.isAfter(test1));
        assertEquals(false, test1a.isAfter(test1a));
        
        MutableDateTime test2 = new MutableDateTime(TEST_TIME2);
        assertEquals(false, test1.isAfter(test2));
        assertEquals(true, test2.isAfter(test1));
        
        MutableDateTime test3 = new MutableDateTime(TEST_TIME2, GregorianChronology.getInstance(PARIS));
        assertEquals(false, test1.isAfter(test3));
        assertEquals(true, test3.isAfter(test1));
        assertEquals(false, test3.isAfter(test2));
        
        assertEquals(true, test2.isAfter(new MockInstant()));
        assertEquals(false, test1.isAfter(new MockInstant()));
        
        assertEquals(true, new MutableDateTime(TEST_TIME_NOW + 1).isAfter(null));
        assertEquals(false, new MutableDateTime(TEST_TIME_NOW).isAfter(null));
        assertEquals(false, new MutableDateTime(TEST_TIME_NOW - 1).isAfter(null));
    }
    
    //-----------------------------------------------------------------------
    /* //BEGIN GWT IGNORE
    public void testSerialization() throws Exception {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        byte[] bytes = baos.toByteArray();
        oos.close();
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        MutableDateTime result = (MutableDateTime) ois.readObject();
        ois.close();
        
        assertEquals(test, result);
    }
    //END GWT IGNORE */

    //-----------------------------------------------------------------------
    public void testToString() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString());
        
        test = new MutableDateTime(TEST_TIME_NOW, PARIS);
        assertEquals("2002-06-09T02:00:00.000+02:00", test.toString());
    }

    public void testToString_String() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("2002 01", test.toString("yyyy HH"));
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString((String) null));
    }

    //GWT result depends on java.util.Locale / java.util.TimeZone
    public void testToString_String_String() {
        MutableDateTime test = new MutableDateTime(TEST_TIME_NOW);
        assertEquals("Sun 9/6", test.toString("EEE d/M", LocaleInfo.getCurrentLocale()));
//        assertEquals("dim. 9/6", test.toString("EEE d/M", Locale.FRENCH));
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString(null, LocaleInfo.getCurrentLocale()));
        assertEquals("Sun 9/6", test.toString("EEE d/M", null));
        assertEquals("2002-06-09T01:00:00.000+01:00", test.toString(null, null));
    }

    public void testToString_DTFormatter() {
        DateMidnight test = new DateMidnight(TEST_TIME_NOW);
        assertEquals("2002 00", test.toString(DateTimeFormat.forPattern("yyyy HH")));
        assertEquals("2002-06-09T00:00:00.000+01:00", test.toString((DateTimeFormatter) null));
    }

    //-----------------------------------------------------------------------
    public void testToInstant() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Instant result = test.toInstant();
        assertEquals(TEST_TIME1, result.getMillis());
    }

    public void testToDateTime() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTime();
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS), result.getChronology());
    }

    public void testToDateTimeISO() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        DateTime result = test.toDateTimeISO();
        assertSame(DateTime.class, result.getClass());
        assertSame(ISOChronology.class, result.getChronology().getClass());
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS), result.getChronology());
    }

    public void testToDateTime_DateTimeZone() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(test.getChronology(), result.getChronology());
        assertEquals(LONDON, result.getZone());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(PARIS, result.getZone());

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(GregorianChronology.getInstance(LONDON), result.getChronology());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(LONDON, result.getZone());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(LONDON, result.getZone());
        assertEquals(ISOChronology.getInstance(), result.getChronology());
    }

    public void testToDateTime_Chronology() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(GregorianChronology.getInstance(PARIS), result.getChronology());

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());
    }

    public void testToMutableDateTime() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTime();
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS), result.getChronology());
    }

    public void testToMutableDateTimeISO() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1, PARIS);
        MutableDateTime result = test.toMutableDateTimeISO();
        assertSame(MutableDateTime.class, result.getClass());
        assertSame(ISOChronology.class, result.getChronology().getClass());
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS), result.getChronology());
        assertNotSame(test, result);
    }

    public void testToMutableDateTime_DateTimeZone() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(LONDON);
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(LONDON), result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(PARIS);
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(PARIS), result.getChronology());

        test = new MutableDateTime(TEST_TIME1, PARIS);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((DateTimeZone) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());
    }

    public void testToMutableDateTime_Chronology() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.toMutableDateTime(ISOChronology.getInstance());
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime(GregorianChronology.getInstance(PARIS));
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(GregorianChronology.getInstance(PARIS), result.getChronology());

        test = new MutableDateTime(TEST_TIME1, GregorianChronology.getInstance(PARIS));
        result = test.toMutableDateTime((Chronology) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());

        test = new MutableDateTime(TEST_TIME1);
        result = test.toMutableDateTime((Chronology) null);
        assertTrue(test != result);
        assertEquals(test.getMillis(), result.getMillis());
        assertEquals(ISOChronology.getInstance(), result.getChronology());
    }

    public void testToDate() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        Date result = test.toDate();
        assertEquals(test.getMillis(), result.getTime());
    }

    public void testCopy() {
        MutableDateTime test = new MutableDateTime(TEST_TIME1);
        MutableDateTime result = test.copy();
        assertEquals(true, test.equals(result));
        assertEquals(true, test != result);
    }

    public void testRounding1() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals("2002-06-09T05:00:00.000+01:00", test.toString());
        assertEquals(MutableDateTime.ROUND_FLOOR, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
    }

    public void testRounding2() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals("2002-06-09T06:00:00.000+01:00", test.toString());
        assertEquals(MutableDateTime.ROUND_CEILING, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
    }

    public void testRounding3() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T05:00:00.000+01:00", test.toString());
        assertEquals(MutableDateTime.ROUND_HALF_CEILING, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_CEILING);
        assertEquals("2002-06-09T06:00:00.000+01:00", test.toString());
    }

    public void testRounding4() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:00.000+01:00", test.toString());
        assertEquals(MutableDateTime.ROUND_HALF_FLOOR, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_FLOOR);
        assertEquals("2002-06-09T05:00:00.000+01:00", test.toString());
    }

    public void testRounding5() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T05:00:00.000+01:00", test.toString());
        assertEquals(MutableDateTime.ROUND_HALF_EVEN, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
        
        test = new MutableDateTime(2002, 6, 9, 5, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T06:00:00.000+01:00", test.toString());
        
        test = new MutableDateTime(2002, 6, 9, 4, 30, 0, 0);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_HALF_EVEN);
        assertEquals("2002-06-09T04:00:00.000+01:00", test.toString());
    }

    public void testRounding6() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals("2002-06-09T05:06:07.008+01:00", test.toString());
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
        assertEquals(null, test.getRoundingField());
    }

    public void testRounding7() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        try {
            test.setRounding(ISOChronology.getInstance().hourOfDay(), -1);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    public void testRounding8() {
        MutableDateTime test = new MutableDateTime(2002, 6, 9, 5, 6, 7, 8);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
        assertEquals(null, test.getRoundingField());
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_CEILING);
        assertEquals(MutableDateTime.ROUND_CEILING, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
        
        test.setRounding(ISOChronology.getInstance().hourOfDay(), MutableDateTime.ROUND_NONE);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
        assertEquals(null, test.getRoundingField());
        
        test.setRounding(null, -1);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
        assertEquals(null, test.getRoundingField());
        
        test.setRounding(ISOChronology.getInstance().hourOfDay());
        assertEquals(MutableDateTime.ROUND_FLOOR, test.getRoundingMode());
        assertEquals(ISOChronology.getInstance().hourOfDay(), test.getRoundingField());
        
        test.setRounding(null);
        assertEquals(MutableDateTime.ROUND_NONE, test.getRoundingMode());
        assertEquals(null, test.getRoundingField());
    }

    //-----------------------------------------------------------------------
    public void testProperty() {
        MutableDateTime test = new MutableDateTime();
        assertEquals(test.year(), test.property(DateTimeFieldType.year()));
        assertEquals(test.dayOfWeek(), test.property(DateTimeFieldType.dayOfWeek()));
        assertEquals(test.secondOfMinute(), test.property(DateTimeFieldType.secondOfMinute()));
        assertEquals(test.millisOfSecond(), test.property(DateTimeFieldType.millisOfSecond()));
        DateTimeFieldType bad = new DateTimeFieldType("bad") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 3266284396556931810L;
			public DurationFieldType getDurationType() {
                return DurationFieldType.weeks();
            }
            public DurationFieldType getRangeDurationType() {
                return null;
            }
            public DateTimeField getField(Chronology chronology) {
                return UnsupportedDateTimeField.getInstance(this, UnsupportedDurationField.getInstance(getDurationType()));
            }
        };
        try {
            test.property(bad);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            test.property(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
