package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ticket;
import persistence.DbStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HallService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(DbStore.instOf().findAllTickets());
        output.write(json.getBytes(StandardCharsets.UTF_8));
        req.setAttribute("ticketsList", DbStore.instOf().findAllTickets());
        output.flush();
        output.close();
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Ticket place = GSON.fromJson(req.getReader(), Ticket.class);
        int session_id = 0;
        var context = req.getServletContext();
        context.setAttribute("row" , place.getRow());
        context.setAttribute("cell", place.getCell());
        context.setAttribute("session_id", session_id);
    }
}
