package md.utm.tmps.domain.factory.abstractfactory;

import md.utm.tmps.domain.models.*;

// Concrete Factory for AMD-based systems
public class AMDPartsFactory implements ComputerPartsFactory {

    @Override
    public CPU createCPU() {
        return new CPU("AMD", "Ryzen 7 5800X", 8, 3.8);
    }

    @Override
    public GPU createGPU() {
        return new GPU("AMD", "RX 6800 XT", 16);
    }

    @Override
    public RAM createRAM() {
        return new RAM("G.Skill", 32, 3200);
    }

    @Override
    public Storage createStorage() {
        return new Storage("NVMe SSD", 1000, "WD Black SN850");
    }
}