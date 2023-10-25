package com.practice.spring.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Data
@AllArgsConstructor
public class LookupInfo<T, I> {
  JpaRepository<T, I> repo;
  Class lookupClass;
}
