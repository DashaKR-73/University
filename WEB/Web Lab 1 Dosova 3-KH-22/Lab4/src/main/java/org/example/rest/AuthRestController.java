package org.example.rest;

import org.example.config.JwtTokenProvider;
import org.example.error.CreateAccountException;
import org.example.error.LoginException;
import org.example.model.AccountEntity;
import org.example.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestController.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AccountRepository accountRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @GetMapping("authorisation")
    public String auth(
            @RequestParam(value = "login", defaultValue = "") String login,
            @RequestParam(value = "password", defaultValue = "") String password
    ) throws LoginException {

        AccountEntity account = accountRepository.findByLogin(login);

        if (account == null) {
            LOGGER.warn("IN login - помилка авторизації, акаунт з логіном {} не знайден", login);
            throw new LoginException("Акаунт з логіном " + login + " не знайден", LoginException.INCORRECT_LOGIN);
        }

        if (!passwordEncoder.matches(password, account.getHashPass())) {
            LOGGER.warn("IN login - помилка авторизації, не вірно вказаний пароль");
            throw new LoginException("Не вірно вказаний пароль", LoginException.INCORRECT_PASS);
        }

        String token = jwtTokenProvider.createToken(account);

        LOGGER.info("IN login - акауну видан JWT {login=" + login + " | token=" + token + "}");

        return token;
    }

    @GetMapping("registration")
    public void reg(
            @RequestParam(value = "login", defaultValue = "") String login,
            @RequestParam(value = "password", defaultValue = "") String password
    ) throws Exception {
        if (login.isEmpty()) {
            LOGGER.warn("IN reg - помилка при створені акаунту, не можливо створити акаут з пустим логіном");
            throw new CreateAccountException("Не можливо створити аккаут з пустим логіном", CreateAccountException.EMPTY_LOGIN);
        }

        if (password.length() < 8) {
            LOGGER.warn("IN reg - помилка при створені аккаунту, не можливо створити акаунт з паролем меньше ніж 8 символів");
            throw new CreateAccountException("Пароль має бути мінімум 8 символів", CreateAccountException.TOO_SHORT_PASS);
        }

        if (accountRepository.findByLogin(login) != null) {
            LOGGER.warn("IN reg - помилка при створені акаунту, акаунт з таким логіном вже є");
            throw new CreateAccountException("Акаунт з таким логіном вже існує", CreateAccountException.LOGIN_IS_ALREADY);
        }

        AccountEntity newAccount = new AccountEntity();
        newAccount.setLogin(login);
        newAccount.setHashPass(passwordEncoder.encode(password));
        accountRepository.save(newAccount);
    }

}
