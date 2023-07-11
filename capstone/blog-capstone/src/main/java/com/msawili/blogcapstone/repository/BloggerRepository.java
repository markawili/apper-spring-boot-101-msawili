package com.msawili.blogcapstone.repository;

import com.msawili.blogcapstone.model.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, String> {
}
