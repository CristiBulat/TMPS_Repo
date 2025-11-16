package md.utm.tmps.domain.models;

// Simple factory for creating pre-configured computers
// This is NOT a design pattern implementation, just a utility class
public class ComputerFactory {

    public static Computer createGamingComputer() {
        return new Computer(
                "Gaming Beast",
                "Intel Core i7-12700K (12 cores @ 3.6 GHz)",
                "NVIDIA RTX 3080 (10GB VRAM)",
                "Corsair 32GB DDR4-3600",
                "Samsung 980 Pro NVMe SSD 1000GB",
                "Mid Tower RGB",
                1500.0
        );
    }

    public static Computer createWorkstation() {
        return new Computer(
                "Professional Workstation",
                "Intel Xeon W-3275 (28 cores @ 2.5 GHz)",
                "NVIDIA RTX A6000 (48GB VRAM)",
                "Kingston 128GB DDR4-3200",
                "Samsung 980 Pro NVMe SSD 2000GB",
                "Full Tower",
                3500.0
        );
    }

    public static Computer createBudgetPC() {
        return new Computer(
                "Budget Gaming PC",
                "AMD Ryzen 5 5600X (6 cores @ 3.7 GHz)",
                "NVIDIA RTX 3060 (12GB VRAM)",
                "Corsair 16GB DDR4-3200",
                "Kingston A2000 SSD 500GB",
                "Mid Tower",
                900.0
        );
    }

    public static Computer createOfficePC() {
        return new Computer(
                "Office PC",
                "Intel Core i3-12100 (4 cores @ 3.3 GHz)",
                "Intel UHD Graphics (Integrated)",
                "Crucial 8GB DDR4-2666",
                "WD Blue SSD 256GB",
                "Small Form Factor",
                500.0
        );
    }

    public static Computer createCustomComputer(String name, String cpu, String gpu,
                                                String ram, String storage, String caseType, double price) {
        return new Computer(name, cpu, gpu, ram, storage, caseType, price);
    }
}