package com.example.mertcantoptas.volleyloginregister.Model;

public class Paylasim {
    private int id;
    private String tarih;
    private String icerik;
    private String paylasanEmail;

    public Paylasim() {
    }

    public Paylasim(int id, String tarih, String icerik, String paylasanEmail) {
        this.id = id;
        this.tarih = tarih;
        this.icerik = icerik;
        this.paylasanEmail = paylasanEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getPaylasanEmail() {
        return paylasanEmail;
    }

    public void setPaylasanEmail(String paylasanEmail) {
        this.paylasanEmail = paylasanEmail;
    }
}
