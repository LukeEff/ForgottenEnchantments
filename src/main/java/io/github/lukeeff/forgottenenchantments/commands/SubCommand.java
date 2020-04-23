package io.github.lukeeff.forgottenenchantments.commands;

import io.github.lukeeff.forgottenenchantments.commands.enchants.AbstractEnchant;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class SubCommand implements CommandExecutor {

    private Map<String, SubCommandInterface> subCommands;

    private static final String NOTPLAYER = ChatColor.RED + "You must be a player!";
    private static final String INVALIDSUBCOMMAND = ChatColor.RED + "Usage: /fEnchant <enchantment> <level>";

    public SubCommand(Map<String, SubCommandInterface> subCommandMap) {
        this.subCommands = subCommandMap;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(NOTPLAYER);
        } else if (!subCommands.containsKey(strings[0].toLowerCase()) || strings.length < 2) {
            commandSender.sendMessage(INVALIDSUBCOMMAND);
        } else {
            subCommands.get(strings[0]).onCommand((Player) commandSender, strings);
        }
        return true;
    }

    /**
     * Registers an enchantment as a valid sub command
     * @param name the input a player will execute to call the command
     * @param enchant the code that will be ran when input is executed
     */
    public void registerEnchantSubCommand(String name, AbstractEnchant enchant) {
        subCommands.put(name, enchant);
    }


}
