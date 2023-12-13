package com.battleship.battleship.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.battleship.battleship.models.Ship;
import com.battleship.battleship.models.dtos.StartBattleDTO;
import com.battleship.battleship.repositories.ShipRepository;

@Service
public class BattleService {
    private final ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository= shipRepository;
    }

    public void attack(StartBattleDTO startBattleDTO) {
        Optional<Ship> attackerOpt = this.shipRepository.findById((long) startBattleDTO.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById((long) startBattleDTO.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        int newDefenderHealth = defender.getHealth() - attacker.getPower();

        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            this.shipRepository.save(defender);
        }
    }

}
