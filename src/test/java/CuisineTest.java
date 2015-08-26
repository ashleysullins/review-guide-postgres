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
  public void save_returnsTrueIfNamesAreTheSame()  {
    Cuisine myCuisine = new Cuisine("Italian");
    myCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(myCuisine));
  }
}
