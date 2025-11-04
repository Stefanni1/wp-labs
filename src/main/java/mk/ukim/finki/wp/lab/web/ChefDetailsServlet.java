package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsServlet(SpringTemplateEngine springTemplateEngine, ChefService chefService, DishService dishService) {
        this.springTemplateEngine = springTemplateEngine;
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefIdStr = req.getParameter("chefId");
        String dishId = req.getParameter("dishId");

        if (chefIdStr == null || dishId == null || chefIdStr.isEmpty() || dishId.isEmpty()) {
            resp.sendRedirect("/listChefs?errorMessage=Please select both chef and dish");
            return;
        }

        Long chefId;
        try {
            chefId = Long.parseLong(chefIdStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect("/listChefs?errorMessage=Invalid chef ID");
            return;
        }

        try {
            Chef chef = chefService.addDishToChef(chefId, dishId);

            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(webExchange);
            context.setVariable("ipAddress", req.getRemoteAddr());
            context.setVariable("userAgent", req.getHeader("user-agent"));
            context.setVariable("chef", chef);

            springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/dish?chefId=" + chefIdStr + "&errorMessage=" + e.getMessage().replace(" ", "%20"));
        }
    }
}

