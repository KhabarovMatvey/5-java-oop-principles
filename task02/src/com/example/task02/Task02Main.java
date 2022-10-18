package com.example.task02;

public class Task02Main {

    private static final Item ITEM1 = new Item("Товар 1", 10);
    private static final Item ITEM2 = new Item("Товар 2", 20);
    private static final Item ITEM3 = new Item("Товар 3", 30);

    public static void main(String[] args) {
        System.out.println("=== Обычный счет ===");
        Bill bill = new Bill();
        bill.add(ITEM1, 10);
        bill.add(ITEM2, 3);
        bill.add(ITEM3, 1);
        System.out.println(bill);

        System.out.println("\n=== Счет со скидкой 10% ===");
        DiscountBill discountBill = new DiscountBill(10);
        discountBill.add(ITEM1, 10);
        discountBill.add(ITEM2, 3);
        discountBill.add(ITEM3, 1);
        System.out.println(discountBill);

        System.out.println("\n=== Детали скидки ===");
        System.out.println("Исходная сумма: " + discountBill.getOriginalPrice());
        System.out.println("Процент скидки: " + discountBill.getDiscount() + "%");
        System.out.println("Сумма скидки: " + discountBill.getAbsDiscount());
        System.out.println("Итоговая сумма: " + discountBill.getPrice());
    }
}