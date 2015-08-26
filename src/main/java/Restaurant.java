import java.util.List;
import org.sql2o.*;

public class Restaurant {
  private int id;
  private int cuisine_id;
  private String name;
  private String address;
  private String phone;
  private String website;
  private String rating;
  private String price;
  private String family_friendly;

  public int getId(){
    return id;
  }

  public int getCuisine_id(){
    return cuisine_id;
  }

  public String getName(){
    return name;
  }

  public String getAddress(){
    return address;
  }

  public String getPhone(){
    return phone;
  }

  public String getWebsite(){
    return website;
  }

  public String getRating(){
    return rating;
  }

  public String getPrice(){
    return price;
  }

  public String getFamily_Friendly(){
    return family_friendly;
  }

  public Restaurant(String name, int cuisine_id, String address, String phone, String website, String rating, String price, String family_friendly) {
    this.name = name;
    this.cuisine_id = cuisine_id;
    this.address = address;
    this.phone = phone;
    this.website = website;
    this.rating = rating;
    this.price = price;
    this.family_friendly = family_friendly;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getId() == newRestaurant.getId() &&
             this.getCuisine_id() == newRestaurant.getCuisine_id() &&
             this.getAddress() == newRestaurant.getAddress() &&
             this.getPhone() == newRestaurant.getPhone() &&
             this.getWebsite() == newRestaurant.getWebsite() &&
             this.getRating() == newRestaurant.getRating() &&
             this.getPrice() == newRestaurant.getPrice() &&
             this.getFamily_Friendly() == newRestaurant.getFamily_Friendly();
       }
      }

    public static List<Restaurant> all() {
      String sql ="SELECT * FROM restaurant";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Restaurant.class);
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO restaurant (name, cuisine_id, address, phone, website, rating, price, family_friendly) VALUES (:name, :cuisine_id, :address, :phone, :website, :rating, :price, :family_friendly)";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("cuisine_id", this.cuisine_id)
        .addParameter("address", this.address)
        .addParameter("phone", this.phone)
        .addParameter("website", this.website)
        .addParameter("rating", this.rating)
        .addParameter("price", this.price)
        .addParameter("family_friendly", this.family_friendly)
        .executeUpdate()
        .getKey();
      }
    }

    public static Restaurant find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM restaurant WHERE id=:id";
        Restaurant restaurant = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Restaurant.class);
        return restaurant;
      }
    }
}
