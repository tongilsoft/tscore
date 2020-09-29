package com.tongilsoft.tscore.utilidades;

import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PGLoggingInstrumentedTest {
    private static final String TAG = "PGLoggingInstrumentedTest";
    private PGLogging log;
    private String
            dString = "Debug Test",
            iString = "Info Test",
            eString = "Exception Test";
    @Before
    public void setUp() throws Exception {
        log=new PGLogging(true,true,true);
    }

    @Test
    public void testLogging() {
        try{
            log.d(TAG, dString);
            log.i(TAG, iString);
            throw new Exception("Test Exception");
        } catch (Exception e) {
            log.e(TAG, eString, e);
        }
    }

    @After
    public void tearDown() throws Exception {
        log=null;
    }
}