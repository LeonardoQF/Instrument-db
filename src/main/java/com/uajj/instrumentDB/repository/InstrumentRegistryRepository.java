package com.uajj.instrumentDB.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uajj.instrumentDB.model.entities.InstrumentRegistry;

public interface InstrumentRegistryRepository extends JpaRepository<InstrumentRegistry, UUID> {}
