package com.uajj.Tests.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uajj.Tests.model.entities.Instrument;
import com.uajj.Tests.model.entities.enums.InstrumentType;

public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {

	public List<Instrument> findByType(InstrumentType type);

	@Query("SELECT i FROM Instrument i WHERE LOWER(i.brand) LIKE LOWER(CONCAT('%', :text, '%')) "
			+ "OR LOWER(i.type) LIKE LOWER(CONCAT('%', :text, '%')) "
			+ "OR LOWER(i.name) LIKE LOWER(CONCAT('%', :text, '%'))")
	public List<Instrument> searchByAnyText(@Param(value = "text") String text);

}
