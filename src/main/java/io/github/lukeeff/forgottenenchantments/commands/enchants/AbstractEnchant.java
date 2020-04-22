package io.github.lukeeff.forgottenenchantments.commands.enchants;

import io.github.lukeeff.forgottenenchantments.commands.SubCommand;
import io.github.lukeeff.forgottenenchantments.customenchants.EnchantCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractEnchant implements InterfaceEnchant {

    public boolean handleOnCommand(CommandSender sender, String[] args, Enchantment enchant) {
        if(sender instanceof Player && args.length > 0) {
            Player player = (Player) sender;
            processEnchant(player, args, enchant);
        }
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
            return Integer.parseInt(args[0]);
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
