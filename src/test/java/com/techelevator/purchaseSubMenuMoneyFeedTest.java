package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class purchaseSubMenuMoneyFeedTest {
    @Test
    public void testingTenderedMoneyInsidePurchaeMoneyFeed (){
        //arrange
        PurchaseSubMenuMoneyFeed.setTotalTenderedForTest(100);
        //act
        PurchaseSubMenuMoneyFeed.setTotalTendered(3.05);
        double boughtA1Chips = PurchaseSubMenuMoneyFeed.getTotalTendered();
        //assert
        assertEquals(96.95, boughtA1Chips,0);
        //act
        //current amount would be 96.95 - however much the next amount would be
        PurchaseSubMenuMoneyFeed.setTotalTendered(3.65);
        double boughtA4Chips = PurchaseSubMenuMoneyFeed.getTotalTendered();
        //assert
        assertEquals(93.3, boughtA4Chips, 0);
        //act
        //current amount would be 93.30
        PurchaseSubMenuMoneyFeed.setTotalTendered(1.50);
        double boughtC4Drink = PurchaseSubMenuMoneyFeed.getTotalTendered();
        //assert
        assertEquals(91.8, boughtC4Drink,0);
    }
    @Test
    public void testingToSeeIfWeGetTheRightAmountOfChange(){
        PurchaseSubMenuMoneyFeed.setTotalTenderedForTest(100);
        //act
        PurchaseSubMenuMoneyFeed.setTotalTendered(3.05);
        //current amount would be 96.95 - however much the next amount would be
        PurchaseSubMenuMoneyFeed.setTotalTendered(3.65);
        //current amount would be 93.30
        PurchaseSubMenuMoneyFeed.setTotalTendered(1.50);
       //this tests our method returnchange
        PurchaseSubMenuMoneyFeed.returnChange();
        //assert
        assertEquals("\nQuarters Returned: 367\nDimes Returned: 0\nNickels Returned: 1",PurchaseSubMenuMoneyFeed.returnChangeStr);
    }
}
