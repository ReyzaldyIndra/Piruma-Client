package com.fadhlanhawali.bantuanecak;

public class Departemen {
    private String departemen;
    private String fakultas;
    private String jumlah;

    public Departemen(String departemen,String fakultas,String jumlah){
        this.departemen = departemen;
        this.fakultas = fakultas;
        this.jumlah = jumlah;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
