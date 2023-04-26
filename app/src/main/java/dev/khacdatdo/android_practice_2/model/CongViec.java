package dev.khacdatdo.android_practice_2.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int ma;
    private String ten, noiDung, ngayHoanThanh;
    private TinhTrang tinhTrang;
    private boolean isCongTac;

    public CongViec(
            int ma,
            String ten,
            String noiDung,
            String ngayHoanThanh,
            TinhTrang tinhTrang,
            boolean isCongTac
    ) {
        this.ma = ma;
        this.ten = ten;
        this.noiDung = noiDung;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhTrang = tinhTrang;
        this.isCongTac = isCongTac;
    }

    public CongViec(
            String ten, String noiDung, String ngayHoanThanh, TinhTrang tinhTrang, boolean isCongTac
    ) {
        this.ten = ten;
        this.noiDung = noiDung;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhTrang = tinhTrang;
        this.isCongTac = isCongTac;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(String ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public TinhTrang getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(TinhTrang tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public boolean isCongTac() {
        return isCongTac;
    }

    public void setCongTac(boolean congTac) {
        isCongTac = congTac;
    }
}
