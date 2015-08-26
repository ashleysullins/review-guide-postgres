import org.junit.*;
import static org.junit.Assert.*;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void equals_returnTrueIfNamesAretheSame() {
    Cuisine firstCuisine = new Cuisine ("Mexican");
    Cuisine secondCuisine = new Cuisine ("Mexican");
    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_savesIntoDatabase_true()  {
    Cuisine myCuisine = new Cuisine("Italian");
    myCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(myCuisine));
  }

    @Test
    public void find_findCuisineInDatabase_true() {
      Cuisine myCuisine = new Cuisine("noodles");
      myCuisine.save();
      Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
      assertTrue(myCuisine.equals(savedCuisine));
    }
}
