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
        var sessionId = Integer.parseInt(context.getAttribute("session_id").toString());
        var ticket = new Ticket(sessionId, row, cell, 0);
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(ticket);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account acc = GSON.fromJson(req.getReader(), Account.class);
        DbStore.instOf().save(acc);
        var accountId = acc.getId();
        var context = req.getServletContext();
        context.setAttribute("account_id", accountId);
        var row = Integer.parseInt(context.getAttribute("row").toString());
        var cell = Integer.parseInt(context.getAttribute("cell").toString());
        var sessionId = Integer.parseInt(context.getAttribute("session_id").toString());
        var ticket = new Ticket(sessionId, row, cell, accountId);
        DbStore.instOf().save(ticket);
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(ticket);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
