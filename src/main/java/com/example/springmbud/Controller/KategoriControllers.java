package com.example.springmbud.Controller;

import com.example.springmbud.Models.kategori;
import com.example.springmbud.repo.KategoriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class KategoriControllers {

    @Autowired
    private KategoriRepo kategoriRepo;

    // 1. LIHAT SEMUA KATEGORI
    @GetMapping("/kategori/save")
    public ResponseEntity<Map<String, Object>> getListKategori() {
        Map<String, Object> response = new HashMap<>();
        List<kategori> data = kategoriRepo.findAll();

        response.put("status", "success");
        response.put("message", "Data kategori berhasil diambil");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    // 2. TAMBAH KATEGORI BARU
    @PostMapping("/kategori/save")
    public ResponseEntity<Map<String, Object>> saveKategori(@RequestBody kategori kat) {
        Map<String, Object> response = new HashMap<>();

        kategoriRepo.save(kat);

        response.put("status", "success");
        response.put("message", "Kategori berhasil disimpan");
        response.put("data", kat);

        return ResponseEntity.ok(response);
    }

    // 3. UPDATE KATEGORI
    @PutMapping("/kategori/update/{id}")
    public ResponseEntity<Map<String, Object>> updateKategori(@PathVariable Long id, @RequestBody kategori katDetails) {
        Map<String, Object> response = new HashMap<>();

        // Cari data lama
        Optional<kategori> dataLama = kategoriRepo.findById(id);

        if (dataLama.isPresent()) {
            kategori existingKategori = dataLama.get();

            // Update isi data (sesuaikan dengan nama variabel di model kategori.java Anda)
            existingKategori.setNamaKategori(katDetails.getNamaKategori());
            existingKategori.setDeskripsi(katDetails.getDeskripsi());

            // Simpan perubahan
            kategoriRepo.save(existingKategori);

            response.put("status", "success");
            response.put("message", "Kategori berhasil diupdate");
            response.put("data", existingKategori);
            return ResponseEntity.ok(response);

        } else {
            response.put("status", "error");
            response.put("message", "Kategori tidak ditemukan dengan id: " + id);
            return ResponseEntity.status(404).body(response);
        }
    }

    // 4. DELETE KATEGORI
    @DeleteMapping("/kategori/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteKategori(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        if (kategoriRepo.existsById(id)) {
            kategoriRepo.deleteById(id);

            response.put("status", "success");
            response.put("message", "Kategori berhasil dihapus");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Gagal hapus. Kategori tidak ditemukan dengan id: " + id);
            return ResponseEntity.status(404).body(response);
        }
    }
}