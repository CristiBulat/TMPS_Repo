package md.utm.tmps;

import md.utm.tmps.client.ComputerShop;
import md.utm.tmps.domain.factory.abstractfactory.*;
import md.utm.tmps.domain.factory.builder.*;
import md.utm.tmps.domain.factory.factorymethod.*;
import md.utm.tmps.domain.factory.singleton.*;
import md.utm.tmps.domain.models.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   TMPS Lab 1: Creational Design Patterns               ║");
        System.out.println("║   Author: Bulat Cristian                               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        ComputerShop shop = new ComputerShop();

        // 1. SINGLETON PATTERN
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("1️⃣  SINGLETON PATTERN - Configuration Manager");
        System.out.println("═══════════════════════════════════════════════════════════");
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.displayConfigurations();

        // Verify singleton - both references point to same instance
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        System.out.println("\nSingleton verification: " + (config == config2 ? "✓ Same instance" : "✗ Different instances"));

        // 2. FACTORY METHOD PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("2️⃣  FACTORY METHOD PATTERN - Component Creation");
        System.out.println("═══════════════════════════════════════════════════════════");

        ComponentFactory<CPU> cpuFactory = new CPUFactory();
        CPU cpu1 = cpuFactory.createComponent("intel-i9");
        CPU cpu2 = cpuFactory.createComponent("amd-r9");

        ComponentFactory<GPU> gpuFactory = new GPUFactory();
        GPU gpu1 = gpuFactory.createComponent("nvidia-4090");
        GPU gpu2 = gpuFactory.createComponent("amd-6900");

        // 3. ABSTRACT FACTORY PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("3️⃣  ABSTRACT FACTORY PATTERN - Complete System Families");
        System.out.println("═══════════════════════════════════════════════════════════");

        // Intel-based system
        ComputerPartsFactory intelFactory = new IntelPartsFactory();
        Computer intelSystem = shop.buildSystemWithAbstractFactory(intelFactory, "Intel Gaming Rig");
        System.out.println(intelSystem);

        // AMD-based system
        ComputerPartsFactory amdFactory = new AMDPartsFactory();
        Computer amdSystem = shop.buildSystemWithAbstractFactory(amdFactory, "AMD Powerhouse");
        System.out.println(amdSystem);

        // 4. BUILDER PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("4️⃣  BUILDER PATTERN - Custom Computer Assembly");
        System.out.println("═══════════════════════════════════════════════════════════");

        ComputerBuilder builder = new GamingComputerBuilder();
        Computer customPC = shop.buildCustomComputer(
                builder,
                cpu1,
                gpu1,
                new RAM("Corsair Dominator", 64, 3600),
                new Storage("NVMe SSD", 2000, "Samsung 990 Pro")
        );
        System.out.println(customPC);

        // 5. PROTOTYPE PATTERN
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("5️⃣  PROTOTYPE PATTERN - Cloning Pre-configured Systems");
        System.out.println("═══════════════════════════════════════════════════════════");

        Computer budgetPC1 = shop.orderPreConfiguredSystem("budget-gaming", "Alex");
        System.out.println(budgetPC1);

        Computer budgetPC2 = shop.orderPreConfiguredSystem("budget-gaming", "Maria");
        System.out.println(budgetPC2);

        Computer workstation = shop.orderPreConfiguredSystem("workstation", "CompanyXYZ");
        System.out.println(workstation);

        // Verify that clones are independent objects
        System.out.println("Clone independence check: " +
                (budgetPC1 != budgetPC2 ? "✓ Different objects" : "✗ Same object"));

        // Final Summary
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("📊 SUMMARY");
        System.out.println("═══════════════════════════════════════════════════════════");
        shop.displayShopInfo();

        System.out.println("\n✅ All 5 Creational Design Patterns demonstrated successfully!");
    }
}