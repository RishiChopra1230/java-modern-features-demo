package sealedclasses.dot.power;

public sealed interface PowerSource permits ElectricGridSource,GreenPower, ExperimentalPower {
    void drawEnergy();
}








