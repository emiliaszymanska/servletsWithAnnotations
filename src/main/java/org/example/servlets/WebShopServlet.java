package org.example.servlets;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.example.model.Cart;
import org.example.model.Item;
import org.example.model.Stock;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "shop", urlPatterns = {"/shop"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet {

    private Stock stock;

    @Override
    public void init() {
        getStock();
    }

    public List<Item> getStock() {
        this.stock = new Stock();
        stock.add(new Item(0, "Laptop", 2000.0));
        stock.add(new Item(1, "Book", 40.0));
        stock.add(new Item(2, "Cellphone", 700.0));
        stock.add(new Item(3, "Smartwatch", 400.0));

        return stock.getItemsAsList();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/shop.mustache");
        m.execute(response.getWriter(), new WebShopServlet()).flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = new Cart();
        String[] action = request.getParameter("act").split("-");
        Item item = stock.getItemById(Integer.parseInt(action[1]));

        switch (action[0]) {
            case "add" -> cart.add(item);
            case "delete" -> cart.remove(item);
        }
        doGet(request, response);
    }
}
