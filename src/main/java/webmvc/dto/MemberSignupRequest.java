package webmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSignupRequest {

    @NotBlank
    @Email
    private String email;

    @Size(min = 7, max = 14)
    private String password;
}
