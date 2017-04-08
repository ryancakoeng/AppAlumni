package com.ccdp.appalumnii.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lenovo on 23/03/2017.
 */
public class Alumni implements Serializable {

    private int id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nrp")
    private String nrp;
    @SerializedName("id_jurusan")
    private String idJurusan;
    @SerializedName("tgl_lulus")
    private String tglLulus;
    @SerializedName("tempat_kerja")
    private String tempatKerja;
    @SerializedName("telpon")
    private String telpon;
    @SerializedName("email")
    private String email;


    public Alumni(){

    }

    public Alumni(int id, String nama, String nrp, String idJurusan, String tglLulus, String tempatKerja, String telpon, String email) {
        this.id = id;
        this.nama = nama;
        this.nrp = nrp;
        this.idJurusan = idJurusan;
        this.tglLulus = tglLulus;
        this.tempatKerja = tempatKerja;
        this.telpon = telpon;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public String getIdJurusan() {
        return idJurusan;
    }

    public void setIdJurusan(String idJurusan) {
        this.idJurusan = idJurusan;
    }

    public String getTglLulus() {
        return tglLulus;
    }

    public void setTglLulus(String tglLulus) {
        this.tglLulus = tglLulus;
    }

    public String getTempatKerja() {
        return tempatKerja;
    }

    public void setTempatKerja(String tempatKerja) {
        this.tempatKerja = tempatKerja;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alumni alumni = (Alumni) o;

        if (id != alumni.id) return false;
        if (nama != null ? !nama.equals(alumni.nama) : alumni.nama != null) return false;
        if (nrp != null ? !nrp.equals(alumni.nrp) : alumni.nrp != null) return false;
        if (idJurusan != null ? !idJurusan.equals(alumni.idJurusan) : alumni.idJurusan != null)
            return false;
        if (tglLulus != null ? !tglLulus.equals(alumni.tglLulus) : alumni.tglLulus != null)
            return false;
        if (tempatKerja != null ? !tempatKerja.equals(alumni.tempatKerja) : alumni.tempatKerja != null)
            return false;
        if (telpon != null ? !telpon.equals(alumni.telpon) : alumni.telpon != null) return false;
        return email != null ? email.equals(alumni.email) : alumni.email == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nama != null ? nama.hashCode() : 0);
        result = 31 * result + (nrp != null ? nrp.hashCode() : 0);
        result = 31 * result + (idJurusan != null ? idJurusan.hashCode() : 0);
        result = 31 * result + (tglLulus != null ? tglLulus.hashCode() : 0);
        result = 31 * result + (tempatKerja != null ? tempatKerja.hashCode() : 0);
        result = 31 * result + (telpon != null ? telpon.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
