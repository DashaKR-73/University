package org.example.repository;

import org.example.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository
        extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByLogin(String login);

}
