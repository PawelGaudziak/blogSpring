package pl.reaktor.projektblog.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private DataSource dataSource;


    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication()

               //sprawdzenie i pobranie loginu i hasła użytkownika, oraz czy jest aktywny po adresie email
                //który jest u nas w systemie loginem
                .usersByUsernameQuery("SELECT email, password, active FROM user WHERE email=?")
                //pobranie roli użytkownika z bazy danych po podanym emailu(loginie)
                .authoritiesByUsernameQuery("SELECT u.email, r.role FROM user u inner join role r "+
                                             "on r.id=u.role_id WHERE u.email=?")
                //ustanawianie klasy odpowiedzialnej za połączenia do bazy danych
                .dataSource(dataSource)
                //ustawienie sposobu kodownia hasła w bazie danych
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
               //dostęp do adresów admin będzie miał tylko użytkownik o roli admin
                .antMatchers("/admin/*", "/users").hasAuthority("admin")
                //dostęp do artykułów tylko dla admina i usera
                .antMatchers("/article/*", "/article").hasAnyAuthority("user","admin")
                .anyRequest().permitAll()
                .and()
                //domyślna strona do logowania
                .formLogin()
                //nasza strona logowania
                .loginPage("/login")
                //przekierowanie w przypadku powodzenia
                .defaultSuccessUrl("/")
                //przekierowanie w przypadku błędu logowania
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

}
