package com.practice.spring.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.CrudRepository;

@Data
@AllArgsConstructor
public class LookupInfo<T, I> {
  CrudRepository<T, I> repo;
  Class lookupClass;
}
