package io.github.lukeeff.forgottenenchantments.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements CommandExecutor {

    private Map<String, SubCommand> cmds = new HashMap<>();
    private static final String INVALIDSUBCOMMAND = ChatColor.RED + "Error: unknown sub command";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!cmds.containsKey(strings[0].toLowerCase())) {
            commandSender.sendMessage(INVALIDSUBCOMMAND);
        } else {
            //executeCommand(commandSender, command, strings);
        }
        return true;
    }

}
