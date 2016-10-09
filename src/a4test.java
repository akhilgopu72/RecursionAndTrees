import static org.junit.Assert.*;
import static common.JUnitUtil.*;

import java.util.Arrays;
import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

public class a4test {

    private static Network n;
    private static Person[] people;

    @BeforeClass
    public static void setup(){
        n= new Network();
        people= new Person[]{new Person("A", n, 0), new Person("B", n, 0), new Person("C", n, 0),
                new Person("D", n, 0), new Person("E", n, 0), new Person("F", n, 0),
                new Person("G", n, 0), new Person("H", n, 0), new Person("I", n, 0),
                new Person("J", n, 0), new Person("K", n, 0), new Person("L", n, 0)
        };
    }

    @Test
    public void testBuiltInGetters(){
        DiseaseTree dt= new DiseaseTree(people[0]);
        assertEquals(people[0], dt.getRoot());
        assertEquals(0, dt.numberOfChildren());
        assertTrue(dt.copyOfChildren().isEmpty());
    }

    @Test
    public void testAdd() {
        DiseaseTree dt= new DiseaseTree(people[0]); 

        //Test add to root
        DiseaseTree dt2= dt.add(people[0], people[1]);
        assertEquals(people[1], dt2.getRoot());
        assertEquals(1, dt.numberOfChildren());
        assertEquals(0, dt2.numberOfChildren());
        assertTrue(dt.copyOfChildren().contains(dt2));
        assertTrue(dt2.copyOfChildren().isEmpty());

        //Test add to non-root
        DiseaseTree dt3= dt.add(people[1], people[2]);
        assertEquals(people[2], dt3.getRoot());
        assertEquals(1, dt.numberOfChildren());
        assertEquals(1, dt2.numberOfChildren());
        assertEquals(0, dt3.numberOfChildren());
        assertTrue(dt.copyOfChildren().contains(dt2));		
        assertTrue(dt2.copyOfChildren().contains(dt3));
        assertTrue(dt3.copyOfChildren().isEmpty());

        //Test add second child
        DiseaseTree dt4= dt.add(people[0], people[3]);
        assertEquals(people[3], dt4.getRoot());
        assertEquals(2, dt.numberOfChildren());
        assertEquals(1, dt2.numberOfChildren());
        assertEquals(0, dt3.numberOfChildren());
        assertEquals(0, dt4.numberOfChildren());
        assertTrue(dt.copyOfChildren().contains(dt2));	
        assertTrue(dt.copyOfChildren().contains(dt4));		
        assertTrue(dt2.copyOfChildren().contains(dt3));
        assertTrue(dt3.copyOfChildren().isEmpty());
        assertTrue(dt4.copyOfChildren().isEmpty());
    }
    
    @Test
    public void testSize(){
    	DiseaseTree dt= new DiseaseTree(people[0]); 
    	assertEquals(1, dt.size());
    	DiseaseTree dt2= dt.add(people[0], people[1]);
    	assertEquals(2, dt.size());
    	assertEquals(1, dt2.size());
    	DiseaseTree dt3= dt.add(people[1], people[2]);
    	assertEquals(3, dt.size());
    	assertEquals(2, dt2.size());
    	assertEquals(1, dt3.size());
    	DiseaseTree dt4= dt.add(people[0], people[3]);
    	assertEquals(4, dt.size());
    	assertEquals(1, dt4.size());
    	assertEquals(2, dt2.size());
    }
    
    @Test
    public void testDepthOf()
    {
    	DiseaseTree dt= new DiseaseTree(people[0]); 
    	assertEquals(0,dt.depthOf(people[0]));
    	DiseaseTree dt4= dt.add(people[0], people[3]);
    	assertEquals(1, dt.depthOf(people[3]));
    	DiseaseTree dt2= dt.add(people[0], people[1]);
    	assertEquals(1, dt.depthOf(people[1]));
    	assertEquals(0, dt2.depthOf(people[1]));
    	DiseaseTree dt3= dt.add(people[1], people[2]);
    	assertEquals(2, dt.depthOf(people[2]));
    	assertEquals(1, dt2.depthOf(people[2]));
    }
    
    @Test
    public void testWidthAtDepth(){
    	DiseaseTree dt= new DiseaseTree(people[0]);
    	assertEquals(1,dt.widthAtDepth(0));
    	DiseaseTree dt2= dt.add(people[0], people[1]);
    	DiseaseTree dt3= dt.add(people[0], people[2]);
    	assertEquals(2,dt.widthAtDepth(1));
    	DiseaseTree dt4= dt.add(people[1], people[3]);
    	DiseaseTree dt5= dt.add(people[2], people[4]);
    	DiseaseTree dt6= dt.add(people[2], people[5]);
    	assertEquals(3,dt.widthAtDepth(2));
    	DiseaseTree dt7= dt.add(people[4], people[6]);
    	assertEquals(1,dt.widthAtDepth(3));
    	
    }
    @Test
    public void testDiseaseRouteTo()
    {
    	DiseaseTree dt= new DiseaseTree(people[0]);
    	DiseaseTree dt2= dt.add(people[0], people[1]);
    	DiseaseTree dt3= dt.add(people[0], people[2]);
    	DiseaseTree dt4= dt.add(people[1], people[3]);
    	DiseaseTree dt5= dt.add(people[2], people[4]);
    	DiseaseTree dt6= dt.add(people[2], people[5]);
    	DiseaseTree dt7= dt.add(people[4], people[6]);
    	
    }
    @Test
    public void testSharedAncestorOf()
    {
    	DiseaseTree dt= new DiseaseTree(people[0]);
    	DiseaseTree dt2= dt.add(people[0], people[1]);
    	DiseaseTree dt3= dt.add(people[0], people[2]);
    	DiseaseTree dt4= dt.add(people[1], people[3]);
    	DiseaseTree dt5= dt.add(people[2], people[4]);
    	DiseaseTree dt6= dt.add(people[2], people[5]);
    	DiseaseTree dt7= dt.add(people[4], people[6]);
    	
    }
    @Test
    public void testEquals()
    {
    	DiseaseTree dt= new DiseaseTree(people[0]);
    	DiseaseTree dt2= dt.add(people[0], people[1]);
    	DiseaseTree dt3= dt.add(people[0], people[2]);
    	DiseaseTree dt4= dt.add(people[1], people[3]);
    	DiseaseTree dt5= dt.add(people[2], people[4]);
    	DiseaseTree dt6= dt.add(people[2], people[5]);
    	DiseaseTree dt7= dt.add(people[4], people[6]);
    	DiseaseTree dt8= new DiseaseTree(people[0]);
    	DiseaseTree dt9= dt8.add(people[0], people[1]);
    	DiseaseTree dt10= dt8.add(people[0], people[2]);
    	DiseaseTree dt11= dt8.add(people[1], people[3]);
    	DiseaseTree dt12= dt8.add(people[2], people[4]);
    	DiseaseTree dt13= dt8.add(people[2], people[5]);
    	DiseaseTree dt14= dt8.add(people[4], people[6]);
    	assertEquals(true, dt8.equals(dt));
    	assertEquals(true, dt2.equals(dt9));
    	assertEquals(true, dt3.equals(dt10));
    	assertEquals(true, dt4.equals(dt11));
    	assertEquals(true, dt5.equals(dt12));
    	assertEquals(true, dt2.equals(dt2));
    	assertEquals(false, dt2.equals(dt11));
    	assertEquals(false, dt.equals(dt11));
    	assertEquals(false, dt.equals(5));




    	
    	
    }
    

}
