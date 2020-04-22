package io.github.lukeeff.forgottenenchantments.commands.enchants;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface InterfaceEnchant {

    int DEFAULTLEVEL = 1;

    /**
     * Sets the enchantment lore of a target ItemStack
     * @param tool the ItemStack object that will have lore added to
     * @param enchant the Enchantment that will be added
     * @param level the level of the Enchantment
     */
    void setEnchantLore(ItemStack tool, Enchantment enchant, int level);

    /**
     * Processes the enchantment to be added to the Item a player is holding
     * in their main hand at the level specified by player input
     * @param player the target player
     * @param args the argument a player specified for a level
     * @param enchant the enchantment to be added
     */
    void processEnchant(Player player, String[] args, Enchantment enchant);

    /**
     * Sets a custom enchantment to an item
     * @param item the item that will be applied the special enchantment
     * @param player the player that is enchanting the item
     * @param level the level of the enchantment
     * @param enchant the enchantment
     */
    void setEnchantment(ItemStack item, Player player, int level, Enchantment enchant);

    Integer getLevel(String[] commandArguments);

}
