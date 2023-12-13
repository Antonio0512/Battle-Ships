package com.battleship.battleship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battleship.battleship.models.Category;
import com.battleship.battleship.models.enums.CategoryEnum;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(CategoryEnum name);
}
