package ru.gb.vending_machine.product;

public class Bottle extends Product {

    private double volume;

    public Bottle(String name, double price, double volume) {
        super(name, price);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", volume=" + volume;
    }
}
