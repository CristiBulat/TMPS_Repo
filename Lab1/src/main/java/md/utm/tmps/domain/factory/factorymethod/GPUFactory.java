package md.utm.tmps.domain.factory.factorymethod;

import md.utm.tmps.domain.models.GPU;

// Concrete Factory for GPUs
public class GPUFactory extends ComponentFactory<GPU> {

    @Override
    public GPU createComponent(String type) {
        return switch (type.toLowerCase()) {
            case "nvidia-3060" -> new GPU("NVIDIA", "RTX 3060", 12);
            case "nvidia-3070" -> new GPU("NVIDIA", "RTX 3070", 8);
            case "nvidia-3080" -> new GPU("NVIDIA", "RTX 3080", 10);
            case "nvidia-4090" -> new GPU("NVIDIA", "RTX 4090", 24);
            case "amd-6700" -> new GPU("AMD", "RX 6700 XT", 12);
            case "amd-6800" -> new GPU("AMD", "RX 6800 XT", 16);
            case "amd-6900" -> new GPU("AMD", "RX 6900 XT", 16);
            default -> throw new IllegalArgumentException("Unknown GPU type: " + type);
        };
    }
}