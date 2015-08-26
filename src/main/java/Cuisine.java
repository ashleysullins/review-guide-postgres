import java.util.List;
import org.sql2o.*;

public class Cuisine {
  private int id;
  private String name;

  public int getId() {
   return id;
   }

  public String getName(){
    return name;
  }

  public Cuisine(String name) {
    this.name = name;
  }

  public static List<Cuisine> all() {
    String sql = "SELECT id, name FROM Cuisine";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  @Override
  public boolean equals (Object otherCuisine){
    if(!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getName().equals(newCuisine.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Cuisine (name) VALUES (:name)";
      con.createQuery(sql)
      .addParameter("name", name)
      .executeUpdate();
    }
  }
}
