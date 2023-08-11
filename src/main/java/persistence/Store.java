package persistence;

import model.Account;
import model.Ticket;

import java.util.Collection;

public interface Store {
    void save(Account account);
    void save(Ticket ticket);
    Collection<Ticket> findAllTickets();
    Account findAccountByEmail(String email);
}
