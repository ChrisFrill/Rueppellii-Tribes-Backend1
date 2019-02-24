package com.greenfox.tribes1.ApplicationUser.DTO;

import com.greenfox.tribes1.Validator.NoSpecialCharacters;
import lombok.*;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicationUserDTO {

  @NotBlank
  @NoSpecialCharacters
  private String username;
  @NotBlank
  private String password;
  private String kingdomName;

}
