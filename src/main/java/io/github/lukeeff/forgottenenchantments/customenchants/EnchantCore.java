package io.github.lukeeff.forgottenenchantments.customenchants;

import io.github.lukeeff.forgottenenchantments.customenchants.wrappers.CustomEnchantWrapper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;

public class EnchantCore {

    private static final String[] NUMERALS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };


    /**
     * This method will alert us if an ItemStack object has an enchantment
     * @param enchantment the target enchantment
     * @return true when the item has that enchantment
     */
    public static boolean hasEnchantment(ItemMeta itemMeta, Enchantment enchantment) {
        return itemMeta.hasEnchant(enchantment);
    }

    /**
     * Gets the level of a target enchantment of an item
     * @param itemMeta the target ItemStack's ItemMeta
     * @param enchantment the target enchantment
     * @return an int that represents the level of the enchantment
     */
    public static int getLevel(ItemMeta itemMeta, Enchantment enchantment) {
        return itemMeta.getEnchantLevel(enchantment);
    }

    /**
     * Get the enchantment name as a String with the level appended to the end
     * @param enchantment the target enchantment
     * @param enchantmentLevel the level of the target enchantment
     * @return a String with the enchantment name and the level appended to the end.
     */
    public static String getEnchantName(final Enchantment enchantment, final int enchantmentLevel) {
        final String enchantName = ((CustomEnchantWrapper) enchantment).getDisplayName();
        final String numeralLevel = getNumeralLevel(enchantmentLevel, enchantment);
        return enchantName + " " + numeralLevel;
    }

    /**
     * Get the level of an enchantment as a roman numeral
     * @param enchantmentLevel the level of the target enchantment
     * @param enchantment the target enchantment
     * @return the appropriate numeral associated with the enchantment value
     * @return "" when the level and is one to symbolize an enchantment like silk touch
     * @return enchantment.level.(number) when the level is outside of our range of roman numerals
     */
    private static String getNumeralLevel(final int enchantmentLevel, final Enchantment enchantment) {
        final int maxLevel = enchantment.getMaxLevel();

        if(enchantmentLevel == 1 && maxLevel == 1) {
            return ""; //Don't show a level
        } else if(enchantmentLevel > 10 || enchantmentLevel < 1) {
            return " enchantment.level." + enchantmentLevel; //Out of our numeral range
        } else {
            return NUMERALS[enchantmentLevel - 1];
        }
    }

    /**
     * Registers an enchantment in the Bukkit Enchantment class
     * @param enchantment the enchantment to be registered
     */
    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
