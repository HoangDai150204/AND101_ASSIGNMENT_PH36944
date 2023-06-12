package model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private String maNV;
    private String tenNV;
    private String tenPB;

    public NhanVien(String maNV, String tenNV, String tenPB) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tenPB = tenPB;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }
}
