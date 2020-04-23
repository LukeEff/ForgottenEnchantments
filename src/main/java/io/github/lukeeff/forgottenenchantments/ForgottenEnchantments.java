package io.github.lukeeff.forgottenenchantments;

import io.github.lukeeff.forgottenenchantments.commands.EnchantSubCommand;
import io.github.lukeeff.forgottenenchantments.commands.enchants.LightningEnchant;
import io.github.lukeeff.forgottenenchantments.customenchants.EnchantInitializer;
import io.github.lukeeff.forgottenenchantments.listeners.CombatEnchantListener;
import org.bukkit.plugin.java.JavaPlugin;


public class ForgottenEnchantments extends JavaPlugin {

    private static ForgottenEnchantments instance;
    EnchantSubCommand enchantSubCommand;

    @Override
    public void onEnable() {
        enchantSubCommand = new EnchantSubCommand();

        instance = this;
        new EnchantInitializer();
        getServer().getPluginManager().registerEvents(new CombatEnchantListener(), this);
        this.getCommand("fenchant").setExecutor(enchantSubCommand);
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        enchantSubCommand.registerEnchantSubCommand("smitten", new LightningEnchant());
    }

    /**
     * Gets an instance of the ForgottenEnchantments class
     * @return an instance of the main class
     */
    public static ForgottenEnchantments getPlugin() {
        return instance;
    }

}
