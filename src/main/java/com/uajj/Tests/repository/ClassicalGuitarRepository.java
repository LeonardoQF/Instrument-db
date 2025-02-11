package com.uajj.Tests.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uajj.Tests.model.entities.ClassicalGuitar;

public interface ClassicalGuitarRepository extends JpaRepository<ClassicalGuitar, UUID> {

}
