package net.nitwit.guinea_pigs.item.custom;

import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;

/**
 * Custom item representing guinea pig droppings.
 * Extends BoneMealItem so it can be used like bonemeal in the game (e.g. fertilizing crops).
 */
public class DroppingsItem extends BoneMealItem {
    public DroppingsItem(Item.Settings Settings) {
        super(Settings);
    }
}
