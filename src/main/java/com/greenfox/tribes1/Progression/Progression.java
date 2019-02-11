package com.greenfox.tribes1.Progression;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Progression {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long model_id;
  private boolean isCreate;
  private String type;
  private Timestamp finished_at;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "kingdom_progression",
          joinColumns = @JoinColumn(name = "progression_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))
  private Kingdom kingdom;

}