package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Exception.TroopIdNotFoundException;
import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopService {

  private TroopRepository troopRepository;

  @Autowired
  public TroopService(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  public Troop save(Troop troop) throws TroopNotValidException {
    if (isValidTroop(troop)) {
      return troopRepository.save(troop);
    }
    throw new TroopNotValidException("Troop is not valid");
  }

  public boolean isValidTroop(Troop troop) {
    return troop != null;
  }

  public Troop findById(Long id) throws TroopIdNotFoundException {
    if (troopRepository.findById(id).isPresent()) {
      return troopRepository.findById(id).get();
    } else throw new TroopIdNotFoundException("There is no Troop with such Id");
  }

  public void upgradeTroop(Troop troopToUpgrade) throws TroopNotValidException {
    troopToUpgrade.setLevel(troopToUpgrade.getLevel() + 1L);
    troopToUpgrade.setHP(troopToUpgrade.getHP() * 1.1);
    save(troopToUpgrade);
  }
}
