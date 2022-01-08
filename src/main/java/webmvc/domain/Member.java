package webmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webmvc.exception.WrongIdPasswordException;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime registerDateTime;

    public Member(String email, String password, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.registerDateTime = registerDateTime;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }
    
    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
