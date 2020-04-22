package io.github.lukeeff.forgottenenchantments.customenchants;

import io.github.lukeeff.forgottenenchantments.customenchants.wrappers.LightningWrapper;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class EnchantInitializer {

    public static Enchantment LIGHTNING;
    private static final String LIGHTNINGKEY = "lightning";
    public static List<Enchantment> customEnchantsList = new ArrayList<>();

    public EnchantInitializer() {
        initializeCombat();
        initializeList();
        registerEnchants();
    }

    private void initializeCombat() {
        LIGHTNING = new LightningWrapper(LIGHTNINGKEY);
    }

    private void initializeList() {
        customEnchantsList.add(LIGHTNING);
    }

    private void registerEnchants() {
        EnchantCore.registerEnchantment(LIGHTNING);
    }
}
