package com.offlinedevstudios.dnsmcb;

import net.minecraft.client.ClientBrandRetriever;



public final class BrandManager {

    public static final BrandManager INSTANCE = new BrandManager();


    public static final int MAX_BRAND_LENGTH = 64;

    private String originalBrand;
    private String activeBrand; 

    private BrandManager() {
    }
    public void init() {
        String detected = ClientBrandRetriever.getClientModName();
        this.originalBrand = (detected == null || detected.isEmpty()) ? "vanilla" : detected.trim();

        DNSMCBConfig config = DNSMCBConfig.load();
        String custom = config.getBrand();
        if (isValid(custom)) {
            this.activeBrand = sanitize(custom);
        } else {
            this.activeBrand = null;
            
            DNSMCBConfig.clearBrand();
        }
    }

    public String getActiveBrand() {
        if (this.activeBrand != null) {
            return this.activeBrand;
        }
        return this.originalBrand != null ? this.originalBrand : "vanilla";
    }

    public String getOriginalBrand() {
        return this.originalBrand != null ? this.originalBrand : "vanilla";
    }

    public String getActiveOverrideOrNull() {
        return this.activeBrand;
    }

    public boolean hasCustomBrand() {
        return this.activeBrand != null;
    }


    public String applyCustomBrand(String raw) {
        String trimmed = (raw == null) ? "" : raw.trim();

        if (trimmed.isEmpty()) {
            return "Client brand cannot be empty.";
        }
        if (trimmed.length() > MAX_BRAND_LENGTH) {
            return "Client brand must be at most " + MAX_BRAND_LENGTH + " characters.";
        }

        this.activeBrand = trimmed;
        DNSMCBConfig.save(trimmed);
        return null;
    }


    public void resetToDefault() {
        this.activeBrand = null;
        DNSMCBConfig.clearBrand();
    }

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        String t = s.trim();
        return !t.isEmpty() && t.length() <= MAX_BRAND_LENGTH;
    }

    public static String sanitize(String s) {
        return (s == null) ? "" : s.trim();
    }
}