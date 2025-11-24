package com.example.springmbud.repo;

import com.example.springmbud.Models.kategori; // Import class kategori (huruf kecil)
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepo extends JpaRepository<kategori, Long> {

}