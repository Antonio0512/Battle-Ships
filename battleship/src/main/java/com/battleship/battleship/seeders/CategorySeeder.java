package com.battleship.battleship.seeders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.battleship.battleship.models.Category;
import com.battleship.battleship.models.enums.CategoryEnum;
import com.battleship.battleship.repositories.CategoryRepository;

@Component
public class CategorySeeder implements CommandLineRunner{

    private CategoryRepository categoryRepository;


    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryEnum.values())
                .map(Category::new)
                .collect(Collectors.toList());

            this.categoryRepository.saveAll(categories);
        }        
    }
    
}
