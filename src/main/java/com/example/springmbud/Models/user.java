package com.example.springmbud.Models;

import jakarta.persistence.*;

@Entity
public class user {

    //table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String nama;

    //setter getter
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
}
