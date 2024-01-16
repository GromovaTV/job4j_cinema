package ru.job4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.service.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HallServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(HallServlet.class.getName());
    private HallService service = new HallService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Start Hall Servlet GET");
        service.handleGet(req, resp);
        LOG.info("Finish Hall Servlet GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Start Hall Servlet POST");
        service.handlePost(req, resp);
        LOG.info("Finish Hall Servlet POST");
    }
}
