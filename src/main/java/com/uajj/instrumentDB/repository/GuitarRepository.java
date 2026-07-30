package com.uajj.instrumentDB.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uajj.instrumentDB.model.entities.Guitar;

public interface GuitarRepository extends JpaRepository<Guitar, UUID> {

}
