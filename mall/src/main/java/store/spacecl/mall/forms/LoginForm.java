package store.spacecl.mall.forms;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {

    private String username; //すべて小文字にすべき!('username'このまま使うべき!)
    private String password;

    public LoginForm() {}

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
