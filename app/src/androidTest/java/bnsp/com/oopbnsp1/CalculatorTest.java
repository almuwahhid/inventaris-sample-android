package bnsp.com.oopbnsp1;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import bnsp.com.oopbnsp1.UjiCoba.UjiCoba;

import static junit.framework.Assert.assertEquals;

/**
 * Created by gueone on 5/5/2017.
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorTest {
    UjiCoba ujiCoba;
    @Before
    public void init(){
        ujiCoba=new UjiCoba();
    }

    @Test
    public void testKali() {
        assertEquals(20, ujiCoba.kali(10, 2),0);
    }

    @Test
    public void testBagi() {
        assertEquals(5, ujiCoba.bagi(10, 2),0);
    }

    @Test
    public void testTambah() {
        assertEquals(12, ujiCoba.tambah(10, 2),0);
    }

    @Test
    public void testKurang() {
        assertEquals(8, ujiCoba.kurang(10, 2),0);
    }
}

