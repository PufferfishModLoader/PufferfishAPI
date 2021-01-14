package me.dreamhopping.pml.api.provider;

public abstract class PufferfishAPIProvider {
    protected static final String[] logChoices = new String[]{"5 more minutes in bed, please? No? Ugh, fine. I'm up! I'm awake!", "Good morning! I'm ready for duty!", "*yawns* It's too early for this...", "Hey PML, PAPI here! Or am I?"};
    private static PufferfishAPIProvider impl;

    public static PufferfishAPIProvider getInstance() {
        return impl;
    }

    public static void setInstance(PufferfishAPIProvider instance) {
        if (impl != null) throw new IllegalStateException("Instance already set");
        impl = instance;
    }

    public abstract void applyTransformers();

    public abstract void applyBindings();
}
