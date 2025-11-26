package md.utm.tmps;

import md.utm.tmps.client.ComputerShop;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   TMPS Lab 3: Behavioral Design Patterns               ║");
        System.out.println("║   Author: Bulat Cristian                               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        ComputerShop shop = new ComputerShop();

        // 1. OBSERVER PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("1️⃣  OBSERVER PATTERN - Event-Driven Notifications");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateObserverPattern();

        // 2. STRATEGY PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("2️⃣  STRATEGY PATTERN - Dynamic Pricing Algorithms");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateStrategyPattern();

        // 3. COMMAND PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("3️⃣  COMMAND PATTERN - Encapsulated Operations with Undo");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateCommandPattern();

        // 4. INTEGRATED DEMONSTRATION
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("4️⃣  INTEGRATED PATTERNS - All Working Together");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.demonstrateIntegratedPatterns();

        // Final Summary
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("📊 FINAL SUMMARY");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.displayShopInfo();

        System.out.println("\n✅ All 3 Behavioral Design Patterns demonstrated successfully!");
        System.out.println("\nPatterns Implemented:");
        System.out.println("  • Observer Pattern - For event-driven order notifications");
        System.out.println("  • Strategy Pattern - For flexible pricing algorithms");
        System.out.println("  • Command Pattern  - For undoable order operations");
    }
}