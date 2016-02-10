package com.mcl.repository;

import com.mcl.domain.Greeting;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kim on 2016-02-10.
 */
public interface GreetingRepository extends CrudRepository<Greeting, Integer> {
}
