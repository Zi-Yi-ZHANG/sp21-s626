package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {

        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        for (int i = 4; i < 7; i++) {
            correct.addLast(i);
            broken.addLast(i);
        }

        for (int j = 0; j < 3; j++) {
            assertEquals(correct.size(), broken.size());
            assertEquals(correct.removeLast(), broken.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {

            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // addLast()
                int randVal = StdRandom.uniform(0, 100);

                correct.addLast(randVal);
                broken.addLast(randVal);
                assertEquals(correct.getLast(), broken.getLast());
//                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {
                // size
                assertEquals(correct.size(), broken.size());
//                System.out.println("size: " + correct.size());

            } else if (operationNumber == 2) {
                // getLast()
                int corrSize = correct.size();
                if (corrSize != 0) {
                    assertEquals(correct.getLast(), broken.getLast());
//                    System.out.println("getLast(" + correct.getLast() + ")");
                }

            } else {
                // removeLast()
                if (correct.size() == 0) continue;
                assertEquals(correct.removeLast(), broken.removeLast());
//                System.out.println("removeLast method was matched.");
            }
        }
    }
}
