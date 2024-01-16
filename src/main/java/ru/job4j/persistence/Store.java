package ru.job4j.persistence;

import ru.job4j.model.Account;
import ru.job4j.model.Ticket;

import java.util.Collection;

public interface Store {

    void save(Account account);

    void save(Ticket ticket);

    Collection<Ticket> findAllTickets();

    Account findAccountByEmail(String email);
}
