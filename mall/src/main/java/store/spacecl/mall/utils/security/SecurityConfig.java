package store.spacecl.mall.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //パスワード暗号化関数
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //このセキュリティフィルターチェーンを通過できなければアクセス不可
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize ->
            //authorize.requestMatchers("/**").permitAll()); //すべてに対して通過許容、今後追加設定してページ別設定が可能

        //「ログイン」による「アクセスルール」
        //ログインなしでアクセスを許可するページ（anonymous/*に通じるアドレスはすべて許可など）
        authorize.requestMatchers("/,","/signup","/login","/anonymous/**").permitAll()
            //特定のユーザー(管理者)にのみ許可されるURL
            .requestMatchers("/admin").hasRole("ADMIN")
            //残りのアドレスの処理規則
            .anyRequest().authenticated())
        //ログイン時に処理されるルール
        .formLogin(formLogin->formLogin
                //ログインする時に入るページ、このロジックを処理しておけば loginpost方式を宣言しなくてもいいです
                .loginPage("/login")
                //ログインに成功すると、該当するアドレスに送ってくれる。（ホーム画面に行くロジック）
                .defaultSuccessUrl("/"))
        //ログアウト時に処理されるルール
        .logout(logout->logout
                //ログアウト時に宣言されたアドレスのロジックを処理します.
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //ログアウト時に該当するアドレスに送信されます.
                .logoutSuccessUrl("/login")
                //セッションに割り当てられたログインユーザーの情報を無効にします
                .invalidateHttpSession(true)
        );
        return httpSecurity.build();
    }

    @Bean //セキュリティ管理者
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
