package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class MainMenuDisplayItemsTest {
    private MainMenuDisplayItems mainMenuDisplayItems;
@Before public void HashSetUpmainMenu(){
   //arrange
    this.mainMenuDisplayItems = new MainMenuDisplayItems();
    }
    @Test
    public void testingdispensingMessageMethod() {
    //act
        mainMenuDisplayItems.dispensingMessage("A1");
        String test1 = mainMenuDisplayItems.dispenseMessageStr;
        mainMenuDisplayItems.dispensingMessage("B4");
        String test2 = mainMenuDisplayItems.dispenseMessageStr;
        mainMenuDisplayItems.dispensingMessage("C2");
        String test3 = mainMenuDisplayItems.dispenseMessageStr;
        mainMenuDisplayItems.dispensingMessage("D3");
        String test4 = mainMenuDisplayItems.dispenseMessageStr;
 //Asssert
        Assert.assertEquals("Crunch Crunch, Yum!",test1);
        Assert.assertEquals("Munch Munch, Yum!",test2);
        Assert.assertEquals("Glug Glug, Yum!",test3);
        Assert.assertEquals("Chew Chew, Yum!",test4);
    }
    @Test
    public void testingRemainingAmountLeftInTheHas(){
        //act
        //A1 should be sold out
        mainMenuDisplayItems.setRemainingAmount("A1");
        mainMenuDisplayItems.setRemainingAmount("A1");
        mainMenuDisplayItems.setRemainingAmount("A1");
        mainMenuDisplayItems.setRemainingAmount("A1");
        mainMenuDisplayItems.setRemainingAmount("A1");
        String test1 = mainMenuDisplayItems.getHashSelection().get("A1").get(3);
        //A2 should have 3 items in it now
        mainMenuDisplayItems.setRemainingAmount("A2");
        mainMenuDisplayItems.setRemainingAmount("A2");
        String test2 =  mainMenuDisplayItems.getHashSelection().get("A2").get(3);
        //C4 should have 4 items in it now
        mainMenuDisplayItems.setRemainingAmount("C4");
       String test3 =  mainMenuDisplayItems.getHashSelection().get("C4").get(3);
        //Assert
        Assert.assertEquals("SOLD OUT",test1);
        Assert.assertEquals("3",test2);
        Assert.assertEquals("4",test3);
    }
}
