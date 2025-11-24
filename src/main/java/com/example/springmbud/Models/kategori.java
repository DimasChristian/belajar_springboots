package com.example.springmbud.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "kategori") // Nama tabel di database
public class kategori {

    // 1. Tipe Data: Long (Angka besar untuk ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. Tipe Data: String (Teks)
    // nullable = false artinya kolom ini WAJIB diisi, tidak boleh kosong
    @Column(name = "nama_kategori", nullable = false)
    private String namaKategori;

    // 3. Tipe Data: String (Teks)
    // Ini boleh kosong (tidak ada nullable = false)
    @Column(name = "deskripsi")
    private String deskripsi;

    // 4. Tipe Data: String (Teks pendek)
    @Column(name = "lokasi_rak")
    private String lokasiRak;

    // --- Constructor Kosong (Wajib untuk JPA) ---
    public kategori() {
    }

    // --- Setter dan Getter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

}