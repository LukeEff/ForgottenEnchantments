package io.github.lukeeff.forgottenenchantments.commands.enchants;

import io.github.lukeeff.forgottenenchantments.commands.SubCommand;
import io.github.lukeeff.forgottenenchantments.commands.SubCommandInterface;
import io.github.lukeeff.forgottenenchantments.customenchants.EnchantCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractEnchant implements InterfaceEnchant, SubCommandInterface {

    Enchantment enchant;
    private final int levelIndex = 1;

    @Override
    public boolean onCommand(Player player, String[] args) {
        processEnchant(player, args, enchant);
        return true;
    }

    @Override
    public void setEnchantLore(ItemStack tool, Enchantment enchant, int level) {
        List<String> lore = new ArrayList<>();
        ItemMeta toolMeta = tool.getItemMeta();
        String enchantLore = ChatColor.GRAY + EnchantCore.getEnchantName(enchant, level);
        lore.add(enchantLore);
        toolMeta.setLore(lore);
        tool.setItemMeta(toolMeta);
    }

    @Override
    public void setEnchantment(ItemStack item, Player player, int level, Enchantment enchantment) {
        item.addUnsafeEnchantment(enchantment, level);
        player.getEquipment().setItemInMainHand(item);
    }

    @Override
    public Integer getLevel(String[] args) {
        try {
            return Integer.parseInt(args[levelIndex]);
        } catch (NumberFormatException nfe) {
            return DEFAULTLEVEL;
        }
    }

    @Override
    public void processEnchant(Player player, String[] args, Enchantment enchant) {
        int level = getLevel(args);
        ItemStack tool = player.getEquipment().getItemInMainHand();
        if(tool.getType() == Material.AIR) {
            return; }
        setEnchantLore(tool, enchant, level);
        setEnchantment(tool, player, level, enchant);
    }

}
