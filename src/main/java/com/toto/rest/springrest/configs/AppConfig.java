package com.toto.rest.springrest.configs;

import com.toto.rest.springrest.accounts.Account;
import com.toto.rest.springrest.accounts.AccountRepository;
import com.toto.rest.springrest.accounts.AccountRole;
import com.toto.rest.springrest.accounts.AccountService;
import com.toto.rest.springrest.common.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ApplicationRunner applicationRunner(){
        return new ApplicationRunner() {

            @Autowired
            AccountService accountService;

            @Autowired
            AppProperties appProperties;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Set<AccountRole> admin_roles = new HashSet<>();
                admin_roles.add(AccountRole.ADMIN);
                admin_roles.add(AccountRole.USER);


                Account admin = Account
                        .builder()
                        .email(appProperties.getAdminUsername())
                        .password(appProperties.getAdminPassword())
                        .roles(admin_roles)
                        .build();
                accountService.saveAccount(admin);

                Set<AccountRole> user_role = new HashSet<>();
                user_role.add(AccountRole.USER);

                Account user = Account.builder()
                        .email(appProperties.getUserUsername())
                        .password(appProperties.getUserPassword())
                        .roles(user_role)
                        .build();
                accountService.saveAccount(user);
            }
        };
    }
}
