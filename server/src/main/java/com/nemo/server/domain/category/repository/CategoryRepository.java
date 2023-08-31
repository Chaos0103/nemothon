package com.nemo.server.domain.category.repository;

import com.nemo.server.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category  c join c.member m where m.email=:email")
    List<Category> findByEmail(@Param("email") String email);
}
