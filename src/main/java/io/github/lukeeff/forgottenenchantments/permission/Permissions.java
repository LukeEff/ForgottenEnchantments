package io.github.lukeeff.forgottenenchantments.permission;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public enum Permissions {

    ENCHANT("forgottenenchants.enchant", "forgottenenchants.*");

    String perm;
    ArrayList<String> hierarchy = new ArrayList<>();

    Permissions(String perm, String... hierarchy) {
        this.perm = perm;
        this.hierarchy.addAll(Arrays.asList(hierarchy));
    }

    public boolean hasPerm(CommandSender sender) {
        if(sender instanceof Player) {
            return hasPerm((Player) sender);
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "Error: Commands must be executed in game!");
            return false;
        }
    }

    public boolean hasPerm(Player player) {
        if (!(player.hasPermission(this.perm))) {
            for (String s : this.hierarchy) {
                if (player.hasPermission(s)) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

}
