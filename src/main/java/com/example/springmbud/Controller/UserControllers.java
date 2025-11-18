package com.example.springmbud.Controller;

import com.example.springmbud.Models.user;
import com.example.springmbud.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional; // Untuk menangani data null

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserControllers {

    @Autowired
    private UserRepo userRepo;

    //page user
    @GetMapping("/user")
    public String index() {
        return "Hello Welcome to User management";
    }

    //page userlist
    @GetMapping("/user/userlist")
    public ResponseEntity<Map<String, Object>> getUser() {
        Map<String, Object> response = new HashMap<>();

        List<user> user = userRepo.findAll();

        response.put("status", "success");
        response.put("message", "User list retrieved successfully");
        response.put("data", user);

        return ResponseEntity.ok(response);
    }

    //page insert user
    @PostMapping("/user/save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody user user) {
        Map<String, Object> response = new HashMap<>();

        userRepo.save(user);

        response.put("status", "success");
        response.put("message", "User saved successfully");

        return ResponseEntity.ok(response);
    }

    // Update user berdasarkan ID
    @PutMapping("/user/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody user userDetails) {
        Map<String, Object> response = new HashMap<>();

        // 1. Cari user berdasarkan ID
        Optional<user> userData = userRepo.findById(id);

        // 2. Cek apakah user ada?
        if (userData.isPresent()) {
            user existingUser = userData.get();

            // 3. Update data (timpa data lama dengan yang baru)
            existingUser.setNama(userDetails.getNama());
            existingUser.setEmail(userDetails.getEmail());

            // 4. Simpan perubahan ke database
            userRepo.save(existingUser);

            response.put("status", "success");
            response.put("message", "User updated successfully");
            response.put("data", existingUser);
            return ResponseEntity.ok(response);
        } else {
            // Jika user tidak ditemukan
            response.put("status", "error");
            response.put("message", "User not found with id: " + id);
            return ResponseEntity.status(404).body(response);
        }
    }

    // Delete user berdasarkan ID
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        // Cek apakah user ada di database
        if (userRepo.existsById(id)) {

            // Hapus user
            userRepo.deleteById(id);

            response.put("status", "success");
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            // Jika ID tidak ditemukan
            response.put("status", "error");
            response.put("message", "Cannot delete. User not found with id: " + id);

            return ResponseEntity.status(404).body(response);
        }
    }
}
