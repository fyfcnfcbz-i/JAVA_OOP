package ru.gb.vending_machine;


import ru.gb.vending_machine.product.Bottle;
import ru.gb.vending_machine.product.Product;
import ru.gb.vending_machine.vending.VendingMachine;

public class Main {
    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();

        Product product1 = new Product("lays", 90);
        Product product2 = new Bottle("coca-cola", 120, 2);
        Product product3 = new Product("twix", 100);

        vendingMachine.addProduct(product1);
        vendingMachine.addProduct(product2);
        vendingMachine.addProduct(product3);

        System.out.println(vendingMachine);

    }
}