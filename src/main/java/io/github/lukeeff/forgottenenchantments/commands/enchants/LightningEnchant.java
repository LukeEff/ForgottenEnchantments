package io.github.lukeeff.forgottenenchantments.commands.enchants;

import io.github.lukeeff.forgottenenchantments.customenchants.EnchantInitializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LightningEnchant extends AbstractEnchant implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return handleOnCommand(commandSender, strings, EnchantInitializer.LIGHTNING);
    }
}
