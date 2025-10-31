package md.utm.tmps.domain.factory.builder;

import md.utm.tmps.domain.models.*;

// Builder Pattern
// Interface for building computers step by step
public interface ComputerBuilder {
    ComputerBuilder setName(String name);
    ComputerBuilder setCPU(CPU cpu);
    ComputerBuilder setGPU(GPU gpu);
    ComputerBuilder setRAM(RAM ram);
    ComputerBuilder setStorage(Storage storage);
    ComputerBuilder setCaseType(String caseType);
    Computer build();
}