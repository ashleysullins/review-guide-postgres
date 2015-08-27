import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String,Object>();
      model.put("cuisine", Cuisine.all());
      model.put("template", "templates/home.vtl");
      model.put("restaurants", Restaurant.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cuisine", Cuisine.all());
      model.put("template", "templates/restaurantform.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/restaurant_form", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/home.vtl");
    String name = request.queryParams("rName");
    int cuisine_id = Integer.parseInt(request.queryParams("cuisine_id"));  //  int i = Integer.parseInt(myString);
    String address = request.queryParams("rAddress");
    String phone = request.queryParams("rPhone");
    String website = request.queryParams("rWebsite");
    String rating = request.queryParams("rStars");
    String price = request.queryParams("rPrice");
    String family_friendly = request.queryParams("rFamily");
    Restaurant myRestaurant = new Restaurant(name, cuisine_id, address, phone, website, rating, price, family_friendly);
    myRestaurant.save();
    model.put("restaurants", Restaurant.all());
    model.put("cuisine", Cuisine.all());
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("restaurant", Restaurant.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  post("/restaurant/:id/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
    restaurant.delete();
    model.put("restaurants", Restaurant.all());
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/restaurant/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("restaurant", Restaurant.find(Integer.parseInt(request.params(":id"))));
    model.put("template", "templates/restaurantformupdate.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/restaurant_update/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
    String name = request.queryParams("rName");
    String address = request.queryParams("rAddress");
    String phone = request.queryParams("rPhone");
    String website = request.queryParams("rWebsite");
    String rating = request.queryParams("rStars");
    String price = request.queryParams("rPrice");
    String family_friendly = request.queryParams("rFamily");
    restaurant.update(name, address, phone, website, rating, price, family_friendly);
    model.put("template", "templates/home.vtl");
    model.put("restaurants", Restaurant.all());
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
  }
}
