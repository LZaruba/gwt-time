/*
 *  Copyright 2001-2005 Stephen Colebourne
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
package org.gwttime.time;

import java.util.Date;

import org.gwttime.time.Chronology;
import org.gwttime.time.DateMidnight;
import org.gwttime.time.DateTimeUtils;
import org.gwttime.time.DateTimeZone;
import org.gwttime.time.Instant;
import org.gwttime.time.chrono.GregorianChronology;
import org.gwttime.time.chrono.ISOChronology;
import org.gwttime.time.gwt.JodaGwtTestCase;

import static org.gwttime.time.gwt.TestConstants.*;


/**
 * This class is a Junit unit test for DateMidnight.
 *
 * @author Stephen Colebourne
 */
public class TestDateMidnight_Constructors extends JodaGwtTestCase {
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
    private long TEST_TIME_NOW_UTC =ConstantsForTesting.TEST_TIME_NOW;
//            (y2002days + 31L + 28L + 31L + 30L + 31L + 9L -1L) * DateTimeConstants.MILLIS_PER_DAY;
    private long TEST_TIME_NOW_LONDON =ConstantsForTesting.TEST_TIME_NOW_LONDON;
//            TEST_TIME_NOW_UTC - DateTimeConstants.MILLIS_PER_HOUR;
    private long TEST_TIME_NOW_PARIS =ConstantsForTesting.TEST_TIME_NOW_PARIS;
//            TEST_TIME_NOW_UTC - 2*DateTimeConstants.MILLIS_PER_HOUR;
    
    // 2002-04-05
    private long TEST_TIME1_UTC =ConstantsForTesting.TEST_TIME1;
//            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            + 12L * DateTimeConstants.MILLIS_PER_HOUR
//            + 24L * DateTimeConstants.MILLIS_PER_MINUTE;
    private long TEST_TIME1_LONDON =ConstantsForTesting.TEST_TIME1_LONDON;
//            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            - DateTimeConstants.MILLIS_PER_HOUR;
    private long TEST_TIME1_PARIS =ConstantsForTesting.TEST_TIME1_PARIS;
//            (y2002days + 31L + 28L + 31L + 5L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            - 2*DateTimeConstants.MILLIS_PER_HOUR;
    
    // 2003-05-06
    private long TEST_TIME2_UTC =ConstantsForTesting.TEST_TIME2;
//            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//            + 14L * DateTimeConstants.MILLIS_PER_HOUR
//            + 28L * DateTimeConstants.MILLIS_PER_MINUTE;
    private long TEST_TIME2_LONDON =ConstantsForTesting.TEST_TIME2_LONDON;
//            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//             - DateTimeConstants.MILLIS_PER_HOUR;
    private long TEST_TIME2_PARIS =ConstantsForTesting.TEST_TIME2_PARIS;
//            (y2003days + 31L + 28L + 31L + 30L + 6L -1L) * DateTimeConstants.MILLIS_PER_DAY
//             - 2*DateTimeConstants.MILLIS_PER_HOUR;
    
    private DateTimeZone zone = null;

    /* Removed for GWT public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } */

    /* Removed for GWT public static TestSuite suite() {
        return new TestSuite(TestDateMidnight_Constructors.class);
    } */

    /* Removed for GWT public TestDateMidnight_Constructors(String name) {
        super(name);
    } */

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        DateTimeUtils.setCurrentMillisFixed(TEST_TIME_NOW_UTC);
        zone = DateTimeZone.getDefault();
        /* //BEGIN GWT IGNORE
        locale = Locale.getDefault();
        //END GWT IGNORE */
        DateTimeZone.setDefault(LONDON);
        /* //BEGIN GWT IGNORE
        //Locale.setDefault(Locale.UK);
        Locale.setDefault(Locale.JAPAN);
        //END GWT IGNORE */
    }

    protected void gwtTearDown() throws Exception {
        super.gwtTearDown();
        DateTimeUtils.setCurrentMillisSystem();
        DateTimeZone.setDefault(zone);
        /* //BEGIN GWT IGNORE
        Locale.setDefault(locale);
        //END GWT IGNORE */
        zone = null;
    }

    //-----------------------------------------------------------------------
    public void testTest() {
        assertEquals(ConstantsForTesting.TEST_TIME_NOW_STRING, new Instant(TEST_TIME_NOW_UTC).toString());
        assertEquals(ConstantsForTesting.TEST_TIME1_STRING, new Instant(TEST_TIME1_UTC).toString());
        assertEquals(ConstantsForTesting.TEST_TIME2_STRING, new Instant(TEST_TIME2_UTC).toString());
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor ()
     */
    public void testConstructor() throws Throwable {
        DateMidnight test = new DateMidnight();
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
    }

    /**
     * Test constructor (DateTimeZone)
     */
    public void testConstructor_DateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight(PARIS);
        assertEquals(ISOChronology.getInstance(PARIS), test.getChronology());
        assertEquals(TEST_TIME_NOW_PARIS, test.getMillis());
    }

    /**
     * Test constructor (DateTimeZone=null)
     */
    public void testConstructor_nullDateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight((DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
    }

    /**
     * Test constructor (Chronology)
     */
    public void testConstructor_Chronology() throws Throwable {
        DateMidnight test = new DateMidnight(GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
    }

    /**
     * Test constructor (Chronology=null)
     */
    public void testConstructor_nullChronology() throws Throwable {
        DateMidnight test = new DateMidnight((Chronology) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor (long)
     */
    public void testConstructor_long1() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    /**
     * Test constructor (long)
     */
    public void testConstructor_long2() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME2_LONDON, test.getMillis());
    }

    /**
     * Test constructor (long, DateTimeZone)
     */
    public void testConstructor_long1_DateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS), test.getChronology());
        assertEquals(TEST_TIME1_PARIS, test.getMillis());
    }

    /**
     * Test constructor (long, DateTimeZone)
     */
    public void testConstructor_long2_DateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS), test.getChronology());
        assertEquals(TEST_TIME2_PARIS, test.getMillis());
    }

    /**
     * Test constructor (long, DateTimeZone=null)
     */
    public void testConstructor_long_nullDateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    /**
     * Test constructor (long, Chronology)
     */
    public void testConstructor_long1_Chronology() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    /**
     * Test constructor (long, Chronology)
     */
    public void testConstructor_long2_Chronology() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME2_UTC, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME2_LONDON, test.getMillis());
    }

    /**
     * Test constructor (long, Chronology=null)
     */
    public void testConstructor_long_nullChronology() throws Throwable {
        DateMidnight test = new DateMidnight(TEST_TIME1_UTC, (Chronology) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor (Object)
     */
    public void testConstructor_Object() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

//    /**
//     * Test constructor (Object)
//     */
//    public void testConstructor_invalidObject() throws Throwable {
//        try {
//            new DateMidnight(new Object());
//            fail();
//        } catch (IllegalArgumentException ex) {}
//    }

//    /**
//     * Test constructor (Object=null)
//     */
//    public void testConstructor_nullObject() throws Throwable {
//        DateMidnight test = new DateMidnight((Object) null);
//        assertEquals(ISOChronology.getInstance(), test.getChronology());
//        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
//    }

    /**
     * Test constructor (Object=null)
     */
//    public void testConstructor_badconverterObject() throws Throwable {
//        try {
//            ConverterManager.getInstance().addInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
//            DateMidnight test = new DateMidnight(new Integer(0));
//            assertEquals(ISOChronology.getInstance(), test.getChronology());
//            assertEquals(0L - DateTimeConstants.MILLIS_PER_HOUR, test.getMillis());
//        } finally {
//            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
//        }
//    }

    /**
     * Test constructor (Date, DateTimeZone)
     */
    public void testConstructor_Object_DateTimeZone() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS), test.getChronology());
        assertEquals(TEST_TIME1_PARIS, test.getMillis());
    }

    /**
     * Test constructor (Date, DateTimeZone=null)
     */
    public void testConstructor_Object_nullDateTimeZone() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    /**
     * Test constructor (Object, DateTimeZone)
     */
//    public void testConstructor_badconverterObject_DateTimeZone() throws Throwable {
//        try {
//            ConverterManager.getInstance().addInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
//            DateMidnight test = new DateMidnight(new Integer(0), GregorianChronology.getInstance());
//            assertEquals(ISOChronology.getInstance(), test.getChronology());
//            assertEquals(0L - DateTimeConstants.MILLIS_PER_HOUR, test.getMillis());
//        } finally {
//            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
//        }
//    }

    /**
     * Test constructor (Date, Chronology)
     */
    public void testConstructor_Object_Chronology() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    /**
     * Test constructor (Date, Chronology=null)
     */
    public void testConstructor_Object_nullChronology() throws Throwable {
        Date date = new Date(TEST_TIME1_UTC);
        DateMidnight test = new DateMidnight(date, (Chronology) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME1_LONDON, test.getMillis());
    }

    /**
     * Test constructor (Object, Chronology)
     */
//    public void testConstructor_badconverterObject_Chronology() throws Throwable {
//        try {
//            ConverterManager.getInstance().addInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
//            DateMidnight test = new DateMidnight(new Integer(0), GregorianChronology.getInstance());
//            assertEquals(ISOChronology.getInstance(), test.getChronology());
//            assertEquals(0L - DateTimeConstants.MILLIS_PER_HOUR, test.getMillis());
//        } finally {
//            ConverterManager.getInstance().removeInstantConverter(MockZeroNullIntegerConverter.INSTANCE);
//        }
//    }

    //-----------------------------------------------------------------------
    /**
     * Test constructor (int, int, int)
     */
    public void testConstructor_int_int_int() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(LONDON, test.getZone());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
        try {
            new DateMidnight(Integer.MIN_VALUE, 6, 9);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(Integer.MAX_VALUE, 6, 9);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 0, 9);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 13, 9);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 6, 0);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 6, 31);
            fail();
        } catch (IllegalArgumentException ex) {}
        new DateMidnight(2002, 7, 31);
        try {
            new DateMidnight(2002, 7, 32);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test constructor (int, int, int, DateTimeZone)
     */
    public void testConstructor_int_int_int_DateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, PARIS);
        assertEquals(ISOChronology.getInstance(PARIS), test.getChronology());
        assertEquals(TEST_TIME_NOW_PARIS, test.getMillis());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
        try {
            new DateMidnight(Integer.MIN_VALUE, 6, 9, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(Integer.MAX_VALUE, 6, 9, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 0, 9, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 13, 9, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 6, 0, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 6, 31, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
        new DateMidnight(2002, 7, 31, PARIS);
        try {
            new DateMidnight(2002, 7, 32, PARIS);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test constructor (int, int, int, DateTimeZone=null)
     */
    public void testConstructor_int_int_int_nullDateTimeZone() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (DateTimeZone) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
    }

    /**
     * Test constructor (int, int, int, Chronology)
     */
    public void testConstructor_int_int_int_Chronology() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, GregorianChronology.getInstance());
        assertEquals(GregorianChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
        try {
            new DateMidnight(Integer.MIN_VALUE, 6, 9, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(Integer.MAX_VALUE, 6, 9, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 0, 9, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 13, 9, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 6, 0, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
        try {
            new DateMidnight(2002, 6, 31, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
        new DateMidnight(2002, 7, 31, GregorianChronology.getInstance());
        try {
            new DateMidnight(2002, 7, 32, GregorianChronology.getInstance());
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    /**
     * Test constructor (int, int, int, Chronology=null)
     */
    public void testConstructor_int_int_int_nullChronology() throws Throwable {
        DateMidnight test = new DateMidnight(2002, 6, 9, (Chronology) null);
        assertEquals(ISOChronology.getInstance(), test.getChronology());
        assertEquals(TEST_TIME_NOW_LONDON, test.getMillis());
        assertEquals(2002, test.getYear());
        assertEquals(6, test.getMonthOfYear());
        assertEquals(9, test.getDayOfMonth());
    }

}
