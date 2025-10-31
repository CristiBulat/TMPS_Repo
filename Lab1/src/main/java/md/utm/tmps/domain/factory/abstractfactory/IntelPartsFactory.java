package md.utm.tmps.domain.factory.abstractfactory;

import md.utm.tmps.domain.models.*;

// Concrete Factory for Intel-based systems with NVIDIA GPU
public class IntelPartsFactory implements ComputerPartsFactory {

    @Override
    public CPU createCPU() {
        return new CPU("Intel", "Core i7-12700K", 12, 3.6);
    }

    @Override
    public GPU createGPU() {
        return new GPU("NVIDIA", "RTX 3080", 10);
    }

    @Override
    public RAM createRAM() {
        return new RAM("Corsair", 32, 3600);
    }

    @Override
    public Storage createStorage() {
        return new Storage("NVMe SSD", 1000, "Samsung 980 Pro");
    }
}