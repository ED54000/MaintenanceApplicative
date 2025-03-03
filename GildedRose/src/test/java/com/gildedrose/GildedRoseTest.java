package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GildedRoseTest {



    @Test
    void TestUpdatedRandom() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items.get(0).name);
    }





    @Test
    void TestUpdatedStillZero(){
        Item[] items =  new Item[] {new Item("ItemBadQuality", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items.get(0).quality);
    }

    @Test
    void TestUpdatedZero(){
        Item[] items =  new Item[] {new Item("ItemPoorQuality", 10, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items.get(0).quality);
    }

    @Test
    void TestUpdated(){
        Item[] items =  new Item[] {new Item("ItemPoorQuality", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items.get(0).quality);
    }

    @Test
    void TestUpdatedLowSellIn(){
        Item[] items =  new Item[] {new Item("ItemPoorQuality", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items.get(0).quality);
    }


    @Test
    void TestAgedBrieQualityUnder49(){
        Item[] items =  new Item[] {new Item("Aged Brie", 0, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }

    @Test
    void TestAgedBrieQuality50(){
        Item[] items =  new Item[] {new Item("Aged Brie", 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }

    @Test
    void TestAgedBrieQualityUnder50(){
        Item[] items =  new Item[] {new Item("Aged Brie", 0, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }
    
    @Test
    void TestAgedBrieQualityUnder49LotSellIn(){
        Item[] items =  new Item[] {new Item("Aged Brie", 10, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, app.items.get(0).quality);
    }

    @Test
    void TestAgedBrieQualityUnder50LotSellIn(){
        Item[] items =  new Item[] {new Item("Aged Brie", 10, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }

    @Test
    void TestConcertLowSellIn(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }

    @Test
    void TestConcert11SellIn(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 11,40)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(41, app.items.get(0).quality);
    }


    @Test
    void TestConcertLowSellInUnder50(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 47)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, app.items.get(0).quality);
    }

    @Test
    void TestConcertHighSellIn(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }

    @Test
    void TestConcert(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items.get(0).quality);
    }

    @Test
    void TestConcertNearEnd(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items.get(0).quality);
    }

    @Test
    void TestConcertNearEndHighQuality(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 3, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items.get(0).quality);
    }


    @Test
    void TestConcertEnd(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items.get(0).quality);
    }



    @Test
    void TestSulfuras(){
        Item[] items = new Item[] {new Item( "Sulfuras, Hand of Ragnaros", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(20, app.items.get(0).quality);
    }

    void TestSulfuras2() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items.get(0).quality);
    }
}
