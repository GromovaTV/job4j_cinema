package ru.job4j.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.model.Account;
import ru.job4j.model.Ticket;
import ru.job4j.persistence.DbStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class PaymentService {

    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var context = req.getServletContext();
        var row = Integer.parseInt(context.getAttribute("row").toString());
        var cell = Integer.parseInt(context.getAttribute("cell").toString());
        var session_id = Integer.parseInt(context.getAttribute("session_id").toString());
        var ticket = new Ticket(session_id, row, cell, 0);
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(ticket);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account acc = GSON.fromJson(req.getReader(), Account.class);
        DbStore.instOf().save(acc);
        var account_id = acc.getId();
        var context = req.getServletContext();
        context.setAttribute("account_id", account_id);
        var row = Integer.parseInt(context.getAttribute("row").toString());
        var cell = Integer.parseInt(context.getAttribute("cell").toString());
        var session_id = Integer.parseInt(context.getAttribute("session_id").toString());
        var ticket = new Ticket(session_id, row, cell, account_id);
        DbStore.instOf().save(ticket);
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(ticket);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
