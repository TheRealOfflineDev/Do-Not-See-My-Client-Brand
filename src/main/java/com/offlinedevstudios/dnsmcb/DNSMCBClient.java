package com.offlinedevstudios.dnsmcb;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;


public final class DNSMCBClient implements ClientModInitializer {

    public static final String MOD_ID = "dnsmcb";

    public static final String KEY_OPEN = "key.dnsmcb.open";


    public static final KeyBinding.Category KEY_CATEGORY =
            KeyBinding.Category.create(Identifier.of(MOD_ID, "category"));

    private static KeyBinding openScreenKey;

    @Override
    public void onInitializeClient() {
        openScreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_U,
                KEY_CATEGORY
        ));


        BrandManager.INSTANCE.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (openScreenKey.wasPressed()) {
                openGui(client);
            }
        });
    }

    private static void openGui(MinecraftClient client) {

        client.setScreen(new DNSMCBScreen(client.currentScreen));
    }
}