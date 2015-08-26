import java.util.List;
import org.sql2o.*;

public class Restaurant {
  private int id;
  private int cuisine_id;
  private String name;

  public int getId(){
    return id;
  }

  public int getCuisine_id(){
    return cuisine_id;
  }

  public String getName(){
    return name;
  }

  public Restaurant(String name, int cuisine_id) {
    this.name = name;
    this.cuisine_id = cuisine_id;
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getId() == newRestaurant.getId() &&
             this.getCuisine_id() == newRestaurant.getCuisine_id();
       }
      }

    public static List<Restaurant> all() {
      String sql ="SELECT id, name, cuisine_id FROM restaurant";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Restaurant.class);
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO restaurant (name, cuisine_id) VALUES (:name, :cuisine_id)";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("cuisine_id", this.cuisine_id)
        .executeUpdate()
        .getKey();
      }
    }
}
