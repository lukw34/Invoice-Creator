package FakturaVat;
/**
 * @author Lukasz Walewski(188584)
 * Kreator Faktur Vat
 *
 * Kompilacja javac KreatorFakturVAT.java
 * Uruchomienie java KreatorFakturVAT
 *
 * Opis: Program służy do  tworzzenia faktur VAT
 *
 * UWAGA!!
 * Klasy: Invoice, Product, Commodity i Service sa statyczne tylko z tego względu iz nie dało sie wyslłac
 * wiecej niz jednego pliku
 */

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Stworzenie klasy KreatorFakturVat z wykorzystaniem wzorca projektowego o nazwie Singleton, którego glownym
 * zalozeniem jest ograniczenie ilosci instancji. W tym przpadku można stworzyc tylko jedna instancje klasy
 * KreatorFaktur
 */


public class KreatorFakturVAT {
    private static KreatorFakturVAT ourInstance = new KreatorFakturVAT(); // przechowywanie instancji klasy

    /**
     *
     * @return zwraca instancje klasy KreatorFakturVAT
     */
    public static KreatorFakturVAT getOurInstance() {
        return ourInstance;
    }

    /**
     * PRYWATNY konstruktor, po to aby ograniczyc ilosc instancji
     */
    private KreatorFakturVAT() {
        System.out.println("Witam w kreatorze Faktur Vat\n");
    }

    /**
     *
     * @return informacje o kliencie lub sprzedawcy
     */
    private Company getInformation() {
        Scanner data = new Scanner(System.in);
        System.out.print("Podaj NIP: ");
        String NIP = data.nextLine();

        System.out.print("Podaj nazwe: ");
        String name = data.nextLine();

        System.out.print("Podaj nazwe ulicy: ");
        String street = data.nextLine();

        System.out.print("Podaj numer budynku: ");
        String streetNumber = data.nextLine();

        System.out.print("Podaj kod pocztowy: ");
        String postCode = data.nextLine();

        System.out.print("Podaj nazwe miejscowosci: ");
        String city = data.nextLine();

        return new Company(NIP, name, street, streetNumber, postCode, city);
    }

    /**
     *
     * @return nowo utworzona fakture
     */
    public Invoice createInvoice() {
        Scanner data = new Scanner(System.in);
        Invoice myInvoice = new Invoice();

        System.out.println("Dane dotyczace sprzedawcy. ");
        myInvoice.setSeller(getInformation());

        System.out.println("\nDane dotyczace klienta. ");
        myInvoice.setClient(getInformation());
        String answer = "y";
        do {
            try {
                addProduct(myInvoice);
            } catch (InputMismatchException e) {
                System.out.println("Błedny format danych. Sprobuj ponownie\n");
                continue;
            }

            System.out.print("Chcesz dodac kolejny  produkt[Y/N]: ");
            answer = data.nextLine();
        } while (answer.toLowerCase().equals("y"));

        return myInvoice;
    }

    /**
     *
     * @param myInvoice referencja do faktury do ktorej metoda ma dodac produkt
     */
    private void addProduct(Invoice myInvoice) {
        Scanner data = new Scanner(System.in);
        System.out.print("Co chcesz dodac? towar(1), usluga(2): ");
        int answer = data.nextInt();
        String rubbish = data.nextLine(); //clear scanner
        System.out.print("Podaj nazwe: ");
        data.reset();

        String productName = data.nextLine();

        System.out.print("Podaj jednostke miary: ");
        String unit = data.nextLine();

        System.out.print("Podaj cene jednostkowa[separator: \",\"]: ");
        double unitPrice = data.nextDouble();

        System.out.print("Podaj ilosc (w podanych jednostkach miary): ");
        int quantity = data.nextInt();

        if (answer == 1) {
            System.out.print("Podaj wartosc podatku (3, 7, 22 [wartosc domyslna w przypadku blednej wrtosci]): ");
            int tax = data.nextInt();
            myInvoice.addCommodity(productName, quantity, unit, unitPrice, tax);
            return;
        }

        System.out.print("Podaj wartosc podatku (7, 22 [wartosc domyslna w przypadku blednej wrtosci]): ");
        int tax = data.nextInt();
        myInvoice.addService(productName, quantity, unit, unitPrice, tax);

    }

    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        KreatorFakturVAT myCreator = KreatorFakturVAT.getOurInstance();
        Invoice myInvoice;
        String answer;
        do {
            myInvoice = myCreator.createInvoice();
            System.out.println(myInvoice.toString());
            System.out.print("\nChcesz stworzyc kolejna  fakture[Y/N]: ");
            answer = data.nextLine();
        } while (answer.toLowerCase().equals("y"));

        System.out.println("Koniec Programu");
    }
}
