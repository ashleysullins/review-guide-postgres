import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
   assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNameAreTheSame() {
    Restaurant firstRestaurant = new Restaurant("Khao Man Gai", 1);
    Restaurant secondRestaurant = new Restaurant("Khao Man Gai", 1);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_saveObjectIntoDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Khoa Man Gai", 1);
    myRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(myRestaurant));
    }

    @Test
    public void save_assignsIdtoObject() {
      Restaurant myRestaurant = new Restaurant("Khao Man Gai", 1);
      myRestaurant.save();
      Restaurant savedRestaurant = Restaurant.all().get(0);
      assertEquals(myRestaurant.getId(), savedRestaurant.getId());
    }
}
