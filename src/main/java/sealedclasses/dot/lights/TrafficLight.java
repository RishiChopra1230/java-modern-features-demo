package sealedclasses.dot.lights;

public sealed abstract class TrafficLight permits VerticalTrafficLight,HorizontalTrafficLight,RailroadLight{
    public void turnRed() {}
    public void turnYellow() {}
    public void turnGreen() {}
}

final class VerticalTrafficLight extends TrafficLight {}
final class HorizontalTrafficLight extends TrafficLight {}