package webmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webmvc.exception.WrongIdPasswordException;

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
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }
    
    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
