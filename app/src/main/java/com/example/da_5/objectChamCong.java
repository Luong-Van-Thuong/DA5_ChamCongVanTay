package com.example.da_5;

public class objectChamCong {
    private int id;
    private String thoigian;
    private String ngaythangnam;

    public objectChamCong(int id, String thoigian, String ngaythangnam) {
        this.id = id;
        this.thoigian = thoigian;
        this.ngaythangnam = ngaythangnam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getThoigian() {
        return thoigian;
    }
    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getNgaythangnam() {
        return ngaythangnam;
    }

    public void setNgaythangnam(String ngaythangnam) {
        this.ngaythangnam = ngaythangnam;
    }
}
