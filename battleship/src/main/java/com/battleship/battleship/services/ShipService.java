package com.battleship.battleship.services;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.battleship.battleship.models.Category;
import com.battleship.battleship.models.Ship;
import com.battleship.battleship.models.User;
import com.battleship.battleship.models.dtos.CreateShipDTO;
import com.battleship.battleship.models.dtos.ShipDTO;
import com.battleship.battleship.models.enums.CategoryEnum;
import com.battleship.battleship.repositories.CategoryRepository;
import com.battleship.battleship.repositories.ShipRepository;
import com.battleship.battleship.repositories.UserRepository;
import com.battleship.battleship.sessions.LoggedUser;

import java.util.List;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ShipService(ShipRepository shipRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository= userRepository;
    }

    public boolean addShip(CreateShipDTO shipDTO, LoggedUser loggedUser) {
        Optional<Ship> byName = this.shipRepository.findByName(shipDTO.getName());

        if (byName.isPresent()) {
            return false;
        }

        CategoryEnum categoryType = CategoryEnum.values()[shipDTO.getCategory()];

        Optional<Category> category = this.categoryRepository.findByName(categoryType);
        Optional<User> owner = this.userRepository.findById(loggedUser.getId());

        Ship ship = new Ship();
        ship.setName(shipDTO.getName());
        ship.setPower(shipDTO.getPower());
        ship.setHealth(shipDTO.getHealth());
        ship.setCreated(shipDTO.getCreated());
        ship.setCategory(category.get());
        ship.setUser(owner.get());

        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipDTO> getShipsOwnedBy(long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                    .stream()
                    .map(ShipDTO::new)
                    .collect(Collectors.toList());  
    }

    public List<ShipDTO> getShipsNotOwnedBy(long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                    .stream()
                    .map(ShipDTO::new)
                    .collect(Collectors.toList()); 
    }

    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findAllByOrderByHealthDescPowerAscNameDesc().stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }
}
