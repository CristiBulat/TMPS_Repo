package md.utm.tmps;

import md.utm.tmps.client.ComputerShop;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   TMPS Lab 2: Structural Design Patterns              ║");
        System.out.println("║   Author: Bulat Cristian                               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        ComputerShop shop = new ComputerShop();

        // 1. ADAPTER PATTERN
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("1️⃣  ADAPTER PATTERN - Legacy System Integration");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateAdapterPattern();

        // 2. DECORATOR PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("2️⃣  DECORATOR PATTERN - Enhanced Computer Features");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateDecoratorPattern();

        // 3. FACADE PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("3️⃣  FACADE PATTERN - Simplified Ordering System");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateFacadePattern();

        // Final Summary
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("📊 SUMMARY");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.displayShopInfo();

        System.out.println("\n✅ All 3 Structural Design Patterns demonstrated successfully!");
    }
}