package cs361.battleships.models;

import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

    public class SubTest {

        @Test
        public void testSub(){
            submarine sub = new submarine();
            sub.setUnderwater();
            assertEquals(5, sub.getSize());
            assertEquals("SUBMARINE", sub.getKind());
            assertEquals(true, sub.getUnderwater());
        }
    }
