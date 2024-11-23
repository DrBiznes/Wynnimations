package me.jamino.wynnimations;

import dev.tr7zw.notenoughanimations.api.NotEnoughAnimationsApi;
import me.jamino.wynnimations.animations.ContentBook;
import net.fabricmc.api.ClientModInitializer;

public class Wynnimations implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NotEnoughAnimationsApi.registerAnimation(new ContentBook());
    }
}