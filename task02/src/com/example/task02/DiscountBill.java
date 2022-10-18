package com.example.task02;

public class DiscountBill extends Bill {
    private final int discount;

    public DiscountBill(int discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }
        this.discount = discount;
    }

    @Override
    public long getPrice() {
        return Math.round(super.getPrice() * (100 - discount) / 100.0);
    }

    public int getDiscount() {
        return discount;
    }

    public long getAbsDiscount() {
        return Math.round(super.getPrice() * discount / 100.0);
    }

    public long getOriginalPrice() {
        return super.getPrice();
    }

    @Override
    public String toString() {
        return String.format(
                "Сумма: %d, Скидка: %d%%, Итог: %d",
                getOriginalPrice(), discount, getPrice()
        );
    }
}