
package com.offlinedevstudios.dnsmcb;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;

public final class DNSMCBScreen extends Screen {

    private static final int PANEL_WIDTH = 280;
    private static final int PANEL_HEIGHT = 216;
    private static final int BUTTON_WIDTH = 180;
    private static final int FIELD_WIDTH = 200;
    private static final int ELEMENT_HEIGHT = 20;

    private final Screen parent;

    private int centerX;
    private int panelY;
    private int fieldX;
    private int fieldY;
    private int buttonY;

    private TextFieldWidget brandField;
    private String currentBrand;
    private String statusMessage = "";
    private boolean statusIsError = false;

    public DNSMCBScreen(Screen parent) {
        super(Text.literal("DNSMCB Settings"));
        this.parent = parent;
        this.currentBrand = BrandManager.INSTANCE.getActiveBrand();
    }

    @Override
    protected void init() {
        this.centerX = this.width / 2;
        this.panelY = Math.max(24, this.height / 2 - PANEL_HEIGHT / 2);

        this.fieldX = this.centerX - FIELD_WIDTH / 2;
        this.fieldY = this.panelY + 52;

        this.brandField = new TextFieldWidget(
                this.textRenderer,
                this.fieldX,
                this.fieldY,
                FIELD_WIDTH,
                ELEMENT_HEIGHT,
                Text.literal("Client Brand")
        );

        this.brandField.setMaxLength(BrandManager.MAX_BRAND_LENGTH);
        this.brandField.setPlaceholder(Text.literal("Enter a brand..."));
        this.brandField.setText(this.currentBrand);

        this.brandField.setChangedListener(text -> {
            this.statusMessage = "";
            this.statusIsError = false;
        });

        this.brandField.setFocused(true);

        this.addDrawableChild(this.brandField);

        this.buttonY = this.fieldY + 32;

        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Apply Changes"),
                        button -> this.applyChanges()
                ).dimensions(
                        this.centerX - BUTTON_WIDTH / 2,
                        this.buttonY,
                        BUTTON_WIDTH,
                        ELEMENT_HEIGHT
                ).build()
        );

        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Reset to Default"),
                        button -> this.resetToDefault()
                ).dimensions(
                        this.centerX - BUTTON_WIDTH / 2,
                        this.buttonY + 24,
                        BUTTON_WIDTH,
                        ELEMENT_HEIGHT
                ).build()
        );

        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Back"),
                        button -> this.close()
                ).dimensions(
                        this.centerX - 70,
                        this.buttonY + 48,
                        140,
                        ELEMENT_HEIGHT
                ).build()
        );
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {
        super.render(context, mouseX, mouseY, delta);

        int panelX = this.centerX - PANEL_WIDTH / 2;
        int panelY = this.panelY;

        context.drawTextWithShadow(
                this.textRenderer,
                Text.literal("DNSMCB Settings"),
                panelX + 15,
                panelY + 14,
                0xFFFFFFFF
        );

        context.drawTextWithShadow(
                this.textRenderer,
                Text.literal("Client Brand"),
                panelX + 15,
                this.fieldY - 14,
                0xFFA0A0A0
        );

        if (!this.statusMessage.isEmpty()) {
            int color = this.statusIsError
                    ? 0xFFFF7A7A
                    : 0xFF7AFF7A;

            int barX = panelX + 14;
            int barW = PANEL_WIDTH - 28;

            context.fill(
                    barX,
                    panelY + 130,
                    barX + barW,
                    panelY + 142,
                    0x66000000
            );

            context.drawCenteredTextWithShadow(
                    this.textRenderer,
                    Text.literal(this.statusMessage),
                    this.centerX,
                    panelY + 133,
                    color
            );
        }
    }

    @Override
    public void renderBackground(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {
        super.renderBackground(context, mouseX, mouseY, delta);

        int panelX = this.width / 2 - PANEL_WIDTH / 2;
        int panelY = Math.max(
                24,
                this.height / 2 - PANEL_HEIGHT / 2
        );

        context.fill(
                panelX,
                panelY,
                panelX + PANEL_WIDTH,
                panelY + PANEL_HEIGHT,
                0xB0000000
        );

        context.fill(
                panelX,
                panelY,
                panelX + PANEL_WIDTH,
                panelY + 1,
                0xFFFFFFFF
        );

        context.fill(
                panelX,
                panelY + PANEL_HEIGHT - 1,
                panelX + PANEL_WIDTH,
                panelY + PANEL_HEIGHT,
                0xFFAAAAAA
        );
    }

    @Override
    public boolean keyPressed(
            KeyInput input
    ) {
        if (input.isEnter() && this.brandField != null && this.brandField.isFocused()) {
            this.applyChanges();
            return true;
        }

        return super.keyPressed(input);
    }

    @Override
    public void close() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (this.parent != null) {
            client.setScreen(this.parent);
        } else {
            client.setScreen(null);
        }
    }

    private void applyChanges() {
        if (this.brandField == null) {
            return;
        }

        String error = BrandManager.INSTANCE.applyCustomBrand(
                this.brandField.getText()
        );

        this.currentBrand = BrandManager.INSTANCE.getActiveBrand();

        if (error != null) {
            this.statusMessage = error;
            this.statusIsError = true;
            return;
        }

        this.brandField.setText(this.currentBrand);

        if (MinecraftClient.getInstance().getNetworkHandler() != null) {
            this.statusMessage =
                    "Disconnect & reconnect to apply changes";
        } else {
            this.statusMessage =
                    "Applied: " + this.currentBrand;
        }

        this.statusIsError = false;
    }

    private void resetToDefault() {
        BrandManager.INSTANCE.resetToDefault();

        this.currentBrand = BrandManager.INSTANCE.getActiveBrand();

        if (this.brandField != null) {
            this.brandField.setText(this.currentBrand);
        }

        this.statusMessage = "Reset to default brand.";
        this.statusIsError = false;
    }
}
