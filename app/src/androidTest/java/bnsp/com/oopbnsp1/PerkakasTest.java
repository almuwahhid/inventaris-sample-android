package bnsp.com.oopbnsp1;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import bnsp.com.oopbnsp1.helper.Handler;
import bnsp.com.oopbnsp1.kelas.PerkakasPecah;

import static junit.framework.Assert.assertEquals;

/**
 * Created by gueone on 5/5/2017.
 */
@RunWith(AndroidJUnit4.class)
public class PerkakasTest {
    PerkakasPecah pecah;
    @Before
    public void init(){
        pecah = new PerkakasPecah();
    }

    @Test
    public void getNama_kategori(){
        assertEquals("Perkakas", pecah.getNama_kategori());
    }
}
