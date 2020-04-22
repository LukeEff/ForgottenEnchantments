package io.github.lukeeff.forgottenenchantments;

import io.github.lukeeff.forgottenenchantments.commands.enchants.LightningEnchant;
import io.github.lukeeff.forgottenenchantments.customenchants.EnchantInitializer;
import io.github.lukeeff.forgottenenchantments.listeners.CombatEnchantListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ForgottenEnchantments extends JavaPlugin {

    private static ForgottenEnchantments instance;

    @Override
    public void onEnable() {
        instance = this;
        new EnchantInitializer();
        getServer().getPluginManager().registerEvents(new CombatEnchantListener(), this);
        this.getCommand("smitten").setExecutor(new LightningEnchant());
    }

    @Override
    public void onDisable() {

    }

    /**
     * Gets an instance of the ForgottenEnchantments class
     * @return an instance of the main class
     */
    public static ForgottenEnchantments getPlugin() {
        return instance;
    }

}
