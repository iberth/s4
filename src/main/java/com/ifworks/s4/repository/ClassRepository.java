package com.ifworks.s4.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ifworks.s4.model.Class;

public interface ClassRepository extends PagingAndSortingRepository<Class, Long> {

	List<Class> findByTitle(@Param("title") String title);

}
