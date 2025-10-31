package md.utm.tmps.domain.factory.builder;

import md.utm.tmps.domain.models.*;

// Concrete Builder for Gaming Computers
public class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        this.computer = new Computer("Gaming PC");
    }

    @Override
    public ComputerBuilder setName(String name) {
        computer.setName(name);
        return this;
    }

    @Override
    public ComputerBuilder setCPU(CPU cpu) {
        computer.setCpu(cpu);
        return this;
    }

    @Override
    public ComputerBuilder setGPU(GPU gpu) {
        computer.setGpu(gpu);
        return this;
    }

    @Override
    public ComputerBuilder setRAM(RAM ram) {
        computer.setRam(ram);
        return this;
    }

    @Override
    public ComputerBuilder setStorage(Storage storage) {
        computer.setStorage(storage);
        return this;
    }

    @Override
    public ComputerBuilder setCaseType(String caseType) {
        computer.setCaseType(caseType);
        return this;
    }

    @Override
    public Computer build() {
        // Validation: Gaming PC must have a GPU
        if (computer.getGpu() == null) {
            throw new IllegalStateException("Gaming PC must have a GPU!");
        }
        return computer;
    }
}