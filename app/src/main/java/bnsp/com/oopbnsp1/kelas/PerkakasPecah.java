package bnsp.com.oopbnsp1.kelas;

/**
 * Created by gueone on 5/5/2017.
 */

public class PerkakasPecah extends Perkakas {
    int berat;

    public PerkakasPecah() {
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    @Override
    public String getNama_kategori() {
        return super.getNama_kategori();
    }

    @Override
    public int getId_kategori() {
        return super.getId_kategori();
    }
}
