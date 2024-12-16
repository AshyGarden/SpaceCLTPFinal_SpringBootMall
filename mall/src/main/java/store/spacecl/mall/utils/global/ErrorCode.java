package store.spacecl.mall.utils.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_PASSWORD("ACCOUNT-001", "パスワードが正しくありません！"),
    ALREADY_EXIST_UNSERNAME("ACCOUNT-002", "名前は既に存在します！");

    private final String code;
    private final String message;
}
