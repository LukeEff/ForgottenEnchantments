package io.github.lukeeff.forgottenenchantments.commands;

import org.bukkit.entity.Player;


public interface SubCommandInterface {
    boolean onCommand(Player player, String[] args);
}
