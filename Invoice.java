package FakturaVat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Invoice {

    String sellDate;
    private static int invoiceCounter = 1;
    private Company client;
    private Company seller;
    private int invoiceId;
    ArrayList<Product> boughtProducts = new ArrayList<Product>();

    public Invoice() {
        invoiceId = invoiceCounter;
        invoiceCounter++;
        sellDate = new SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime());
    }

    /**
     * @return wartosc do zaplaty
     */
    public double toPay() {
        double payment = 0;
        for (Product boughtProduct : boughtProducts) {
            payment = +boughtProduct.getGrossPrice();
        }
        return payment;
        //alternatywna wersja funkcji toPay wymagajaca Java SE 8
        //zakomentowana ponieważ nie wszyscy moga posiadac najnowsza wersje Jav-y
        //return boughtProducts.stream().mapToDouble(Product::getGrossPrice).sum();
    }


    /**
     * @param seller obiekt klasy Company reprezentujacy sprzedawce
     */
    public void setSeller(Company seller) {
        this.seller = seller;
    }

    /**
     * @param client obiekt klasy Company reprezentujacy klienta
     */
    public void setClient(Company client) {
        this.client = client;
    }

    /**
     * Dodaje Towar
     *
     * @param name              nazwa
     * @param quantity          ilosc towaru w jednostkach miary
     * @param unitOfMeasurement jednostka miary
     * @param unitPrice         cena jednostkowa
     * @param tax               Podatek Vat: 7 lub 22
     */
    public void addCommodity(String name, int quantity, String unitOfMeasurement, double unitPrice, int tax) {
        boughtProducts.add(new Commodity(name, quantity, unitOfMeasurement, unitPrice, tax));
    }

    /**
     * Dodaje Usluge
     *
     * @param name              nazwa
     * @param quantity          ilosc towaru w jednostkach miary
     * @param unitOfMeasurement jednostka miary
     * @param unitPrice         cena jednostkowa
     * @param tax               Podatek Vat: 7 lub 22
     */
    public void addService(String name, int quantity, String unitOfMeasurement, double unitPrice, int tax) {
        boughtProducts.add(new Service(name, quantity, unitOfMeasurement, unitPrice, tax));
    }

    /**
     * @return sformatowane dane dotyczace faktury
     * <p>
     * metoda zawiera obsługe wyjatku gdy nie ma informacji o kliencie lub  sprzedawcy
     */
    @Override
    public String toString() {
        String information = "FAKTURA VAT nr ";
        StringBuilder creator = new StringBuilder(information).append(invoiceId).append("\nData sprzedazy: ")
                .append(sellDate).append("\n");
        try {
            creator.append("\nSPRZEDAWCA:\n").append(seller.toString());
        } catch (NullPointerException ignored) {
            creator.append("Brak informacji !!\n");
        }

        try {
            creator.append("\nNABYWCA:\n").append(client.toString());
        } catch (NullPointerException ignored) {
            creator.append("Brak informacji !!\n");
        }

        String header = "\n| Nazwa towaru/uslugi      | j.m  | cena     | ilosc|wart.netto  |st.VAT|wart.brutto  |\n";
        creator.append(header);
        for (int i = 0; i < header.length(); i++) {
            creator.append("-");
        }

        creator.append("\n");
        for (Product boughtProduct : boughtProducts) {
            creator.append(boughtProduct.toString());
        }

        //alternatywna rozwiazanie dla petli for-each wymagajaca Java SE 8
        //zakomentowana ponieważ nie wszyscy moga posiadac najnowsza wersje Jav-y
        //creator.append(boughtProducts.stream().map(Product::toString))  ;
        for (int i = 0; i < header.length(); i++) {
            creator.append("-");
        }

        creator.append("\n");
        creator.append(String.format("Razem do zapłaty %.2f", toPay()));
        return creator.toString();
    }
}