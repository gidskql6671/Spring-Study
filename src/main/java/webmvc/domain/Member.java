package webmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webmvc.exception.WrongPasswordException;

@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String email;
    private String password;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            throw new WrongPasswordException();
        }
        this.password = newPassword;
    }
}
