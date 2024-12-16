package store.spacecl.mall.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberForm {

    @Size(min =2, max=20)
    @NotEmpty(message = "IDは必須項目です.") //条件不充足時、該当メッセージが画面に出力
    private String userName;

    @NotEmpty(message = "パスワードは必須項目です.")
    private String password1;

    @NotEmpty(message = "パスワードの確認は必須項目です.")
    private String password2;

    @Email
    @NotEmpty(message = "メールは必須項目です.")
    private String email;
}
