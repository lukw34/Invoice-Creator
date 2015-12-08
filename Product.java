package FakturaVat;

import java.util.ArrayList;

class Product {
    protected ArrayList<Integer> availableTax; //dostepne wartosci podatkow
    protected int tax;
    protected String name;
    protected int quantity;
    protected double grossPrice;
    protected double unitPrice;
    protected double netPrice;
    protected String unitOfMeasurement;

    public Product(String name, int quantity, String unitOfMeasurement, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.unitPrice = unitPrice;
    }

    /**
     * Oblicza wartosc netto i wartosc brutto
     */
    protected void calculateGrossPrice() {
        netPrice = unitPrice * quantity;
        grossPrice = (tax / 100d) * netPrice + netPrice;
    }


    /**
     *
     * @return nazwa
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return cena jednostkowa
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     *
     * @return ilosc
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @return wartosc brutto
     */
    public double getGrossPrice() {
        return grossPrice;
    }

    /**
     *
     * @return wartosc netto
     */
    public double getNetPrice() {
        return netPrice;
    }

    /**
     *
     * @return jednostka miary
     */
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    /**
     *
     * @return sformatowane dane dotyczace produktu
     */
    @Override
    public String toString() {

        return String.format("| %-25s| %-5s| %9.2f| %5d|%12.2f| %3d%s |%13.2f|\n", name, unitOfMeasurement, unitPrice,
                quantity, netPrice, tax, "%", grossPrice);
    }
}
