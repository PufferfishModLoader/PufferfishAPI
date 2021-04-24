package me.dreamhopping.pml.api;

import me.dreamhopping.pml.api.provider.PufferfishAPIProvider;
import me.dreamhopping.pml.mods.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PufferfishAPI implements Mod {
    public static final Logger LOGGER = LogManager.getLogger("PufferfishAPI");

    public void initialize() {
        LOGGER.info("Waking up PufferfishAPIProvider");

        try {
            Class.forName("me.dreamhopping.pml.api.impl.PufferfishAPIProviderImpl", true, this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            System.err.println("PufferfishAPIProviderImpl doesn't want to wake up... They seemed pretty angry... This is what they said to me:");
            e.printStackTrace();

            System.exit(-1);
        }

        // Do some required things like transformers, class bindings, etc.
        PufferfishAPIProvider.getInstance().applyTransformers();
        PufferfishAPIProvider.getInstance().applyBindings();
    }
}
