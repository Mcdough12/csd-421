import static org.junit.Assert.*;
import org.junit.Test;
import javafx.scene.shape.Circle;

public class StyleTest {

    @Test
    public void testCircleStyles() {
        Circle c1 = new Circle(50);
        c1.getStyleClass().add("plaincircle");

        assertTrue(c1.getStyleClass().contains("plaincircle"));

        Circle c2 = new Circle(50);
        c2.setId("redcircle");

        assertEquals("redcircle", c2.getId());

        Circle c3 = new Circle(50);
        c3.setId("greencircle");

        assertEquals("greencircle", c3.getId());
    }
}
