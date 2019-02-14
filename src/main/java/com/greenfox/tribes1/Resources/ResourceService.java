package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.KingdomElementService;
import com.greenfox.tribes1.TimeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ResourceService implements KingdomElementService<Resource> {

  private ResourceRepository resourceRepository;
  private TimeService timeService;
  private Predicate<Resource> isValid = (a) -> (a != null);

  @Autowired
  public ResourceService(ResourceRepository resourceRepository, TimeService timeService) {
    this.timeService = timeService;
    this.resourceRepository = resourceRepository;
  }

  @Override
  @SneakyThrows
  public Resource findById(Long id) {

    return resourceRepository.findById(id).orElseThrow(()
            -> new NotValidResourceException("There is no Building with such Id"));
  }

  @Override
  public Resource save(Optional<Resource> resource) throws NotValidResourceException {
    return resourceRepository.save(resource
            .orElseThrow(() -> new NotValidResourceException("Resource validation failed")));
  }

  @Override
  public void refresh(Resource kingdomResource) throws NotValidResourceException, DateNotGivenException {
    Long difference = timeService.calculateDifference(kingdomResource.getUpdated_at(), new Timestamp(System.currentTimeMillis()));
    kingdomResource.update(difference);
    save(Optional.of(kingdomResource));
  }
}