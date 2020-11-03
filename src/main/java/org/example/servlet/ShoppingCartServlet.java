package org.example.servlet;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.example.model.Item;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "cart", urlPatterns = {"/cart"}, loadOnStartup = 1)
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/cart.mustache");
        m.execute(response.getWriter(), this).flush();
    }

    public List<Item> getCartItems() {
        return WebShopServlet.cart.getItems();
    }

    public double getSum() {
        return WebShopServlet.cart.getItems().stream().mapToDouble(Item::getPrice).sum();
    }
}
