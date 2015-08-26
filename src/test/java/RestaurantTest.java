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

}
