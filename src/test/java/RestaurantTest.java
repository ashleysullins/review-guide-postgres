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
    Restaurant firstRestaurant = new Restaurant("Khao Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
    Restaurant secondRestaurant = new Restaurant("Khao Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_saveObjectIntoDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Khoa Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
    myRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(myRestaurant));
    }

    @Test
    public void save_assignsIdtoObject() {
      Restaurant myRestaurant = new Restaurant("Khao Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
      myRestaurant.save();
      Restaurant savedRestaurant = Restaurant.all().get(0);
      assertEquals(myRestaurant.getId(), savedRestaurant.getId());
    }

    @Test
    public void find_findsRestaurantInDatabase_true() {
      Restaurant myRestaurant = new Restaurant("Khao Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
      myRestaurant.save();
      Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
      assertTrue(myRestaurant.equals(savedRestaurant));
    }

    @Test
    public void save_savesCuisine_idIntoDB_true() {
      Cuisine myCuisine = new Cuisine("thai");
      myCuisine.save();
      Restaurant savedRestaurant = new Restaurant("Khao Man Gai", myCuisine.getId(), "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
      savedRestaurant.save();
      Restaurant myRestaurant = Restaurant.find(savedRestaurant.getId());
      assertEquals(savedRestaurant.getCuisine_id(), myCuisine.getId());
    }

    @Test
    public void update_updateRestaurantInfo() {
      Restaurant savedRestaurant = new Restaurant("Khao Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
      savedRestaurant.save();
      savedRestaurant.update("McDonalds", "200", "11111111", "blah.com", "4 stars", "$$$", "No");
      assertTrue(Restaurant.all().get(0).equals(savedRestaurant));
    }

    @Test
    public void delete_deleteRestaurantFromDatabase() {
      Restaurant myRestaurant = new Restaurant("Khao Man Gai", 1, "100 Anywhere", "555-555-5555", "yelp.com", "5 stars", "$$", "Yes");
      myRestaurant.save();
      myRestaurant.delete();
      assertEquals(Restaurant.all().size(), 0);
    }
}
