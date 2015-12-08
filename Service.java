package FakturaVat;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Klasa reprezentujaca uslugi dziedziczaca po klasie Product
 */
class Service extends Product {

    public Service(String name, int quantity, String unitOfMeasurement, double unitPrice, int tax) {
        super(name, quantity, unitOfMeasurement, unitPrice); //wywolanie konstruktora klasy bazowej

        /**
         * Jezeli podana zostanie nieprawidlowa stawka podatku vat, to do zmiennej instancyjnej tax zostanie
         * przypisana wartosc domyslna 22
         */
        availableTax = new ArrayList<Integer>(Arrays.asList(22, 7));
        if (!availableTax.contains(tax)) {
            this.tax = availableTax.get(0);
        } else this.tax = tax;

        calculateGrossPrice();
    }
}