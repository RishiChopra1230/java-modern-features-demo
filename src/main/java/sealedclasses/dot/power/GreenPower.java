package sealedclasses.dot.power;

public sealed interface GreenPower extends PowerSource
        permits SolarPower, WindPower {
}
