package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.web.IWebExchange;

import java.io.IOException;

@WebServlet(name = "AddReview", urlPatterns = "/addReview")
public class AddReviewServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final ChefService chefService;

    public AddReviewServlet(SpringTemplateEngine springTemplateEngine, ChefService chefService) {
        this.springTemplateEngine = springTemplateEngine;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefIdStr = req.getParameter("chefId");

        if (chefIdStr == null || chefIdStr.isEmpty()) {
            resp.sendRedirect("/listChefs?errorMessage=Chef ID is missing");
            return;
        }

        Long chefId;
        Chef chef = null;
        try {
            chefId = Long.parseLong(chefIdStr);
            chef = chefService.findById(chefId);

            if (chef == null) {
                resp.sendRedirect("/listChefs?errorMessage=Chef not found");
                return;
            }

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listChefs?errorMessage=Invalid chef ID");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("chef", chef);

        springTemplateEngine.process("addReview.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewText = req.getParameter("review");
        String chefIdStr = req.getParameter("chefId");

        if (chefIdStr == null || chefIdStr.isEmpty() || reviewText == null || reviewText.isEmpty()) {
            resp.sendRedirect("/addReview?chefId=" + chefIdStr + "&errorMessage=Please enter a valid review");
            return;
        }

        Long chefId;
        try {
            chefId = Long.parseLong(chefIdStr);
            Chef chef = chefService.findById(chefId);

            if (chef != null) {

                resp.sendRedirect("/chefDetails?chefId=" + chefId);
            } else {
                resp.sendRedirect("/listChefs?errorMessage=Chef not found");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/listChefs?errorMessage=Invalid chef ID");
        }
    }
}
