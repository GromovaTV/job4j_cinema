package ru.job4j.model;

public class Ticket {

    private int id;
    private int session_id;
    private int row;
    private int cell;
    private int account_id;

    public Ticket(int id, int session_id, int row, int cell, int account_id) {
        this.id = id;
        this.session_id = session_id;
        this.row = row;
        this.cell = cell;
        this.account_id = account_id;
    }

    public Ticket(int session_id, int row, int cell, int account_id) {
        this.session_id = session_id;
        this.row = row;
        this.cell = cell;
        this.account_id = account_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id
                + ", account_id=" + account_id
                + ", session_id=" + session_id
                + ", row=" + row
                + ", cell=" + cell
                + '}';
    }
}
