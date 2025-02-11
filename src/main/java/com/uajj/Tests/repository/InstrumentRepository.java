package com.uajj.Tests.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uajj.Tests.model.entities.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {

}
