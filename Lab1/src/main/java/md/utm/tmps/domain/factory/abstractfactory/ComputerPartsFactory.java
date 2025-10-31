package md.utm.tmps.domain.factory.abstractfactory;

import md.utm.tmps.domain.models.*;

// Abstract Factory Pattern
// Interface for creating families of related products
public interface ComputerPartsFactory {
    CPU createCPU();
    GPU createGPU();
    RAM createRAM();
    Storage createStorage();
}