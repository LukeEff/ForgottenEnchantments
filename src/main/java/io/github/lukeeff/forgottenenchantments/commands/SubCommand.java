package io.github.lukeeff.forgottenenchantments.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

abstract public class SubCommand {

    abstract void onCommand(Player player, Command command, String[] args);

    abstract String getPermission();


}
