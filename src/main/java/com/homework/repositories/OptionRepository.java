package com.homework.repositories;

import com.homework.domain.AdditionalOption;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<AdditionalOption, Integer> {
}
