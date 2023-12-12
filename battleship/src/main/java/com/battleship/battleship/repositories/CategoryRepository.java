package com.battleship.battleship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.battleship.battleship.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
