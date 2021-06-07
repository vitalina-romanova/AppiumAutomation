package tests;

import org.junit.Test;
import tests.MainClass;

public class MainClassTest {

    private final MainClass mainClass;

    public MainClassTest() {
        this.mainClass = new MainClass();
    }

    @Test
    public void testGetLocalNumber(){
        Integer localNumber = mainClass.getLocalNumber();
        if(localNumber == 14){
            System.out.println("Test passed, localNumber equal 14");
        }
        else {
            System.out.println("Test failed, localNumber not equal 14");
        }
    }

    @Test
    public void testGetClassNumber(){
        Integer classNumber = mainClass.getClassNumber();
        if (classNumber > 45){
            System.out.println("Test passed, classNumber > 45");
        }
        else {
            System.out.println("Test failed, classNumber < 45");
        }
    }

    @Test
    public void testGetClassString(){
       String classString = mainClass.getClassString();
       String substring = "hello";
       if (classString.toLowerCase().contains(substring.toLowerCase()) || classString.toUpperCase().contains(substring.toUpperCase())){
           System.out.println("Test passed, ClassString contains the string " + substring);
       }
       else {
           System.out.println("Test failed, ClassString not contains the string " + substring);
       }
    }
}
