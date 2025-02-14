package com.uajj.Tests.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uajj.Tests.model.entities.Guitar;

public interface GuitarRepository extends JpaRepository<Guitar, UUID> {

}
