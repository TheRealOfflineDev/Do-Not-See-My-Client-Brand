package com.offlinedevstudios.dnsmcb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public final class DNSMCBConfig {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public String brand = "";

    private DNSMCBConfig() {
    }

    public static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("dnsmcb.json");
    }

    public static DNSMCBConfig load() {
        Path path = getConfigPath();
        if (!Files.exists(path)) {
            return new DNSMCBConfig();
        }
        try {
            String json = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            DNSMCBConfig cfg = GSON.fromJson(json, DNSMCBConfig.class);
            if (cfg == null) {
                cfg = new DNSMCBConfig();
            }
            cfg.normalize();
            return cfg;
        } catch (IOException | RuntimeException e) {
            DNSMCBConfig cfg = new DNSMCBConfig();
            save(cfg);
            return cfg;
        }
    }

    public String getBrand() {
        return this.brand == null ? "" : this.brand;
    }

    private void normalize() {
        if (this.brand == null) {
            this.brand = "";
        } else {
            this.brand = this.brand.trim();
        }
    }

    public static void save(String customBrand) {
        DNSMCBConfig cfg = new DNSMCBConfig();
        cfg.brand = (customBrand == null) ? "" : customBrand.trim();
        save(cfg);
    }

    public static void clearBrand() {
        DNSMCBConfig cfg = load();
        if (!cfg.getBrand().isEmpty()) {
            save(cfg);
        }
    }

    private static void save(DNSMCBConfig cfg) {
        try {
            Path path = getConfigPath();
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(cfg), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save DNSMCB config. Open an issue report at https://github.com/therealofflinedev/DNSMCB", e);
        }
    }
}