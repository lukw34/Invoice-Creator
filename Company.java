package FakturaVat;

/**
 * Klasa reprezentujaca firme
 */
class Company {
    private String name;
    private String NIP;
    private Adress corespondentAddress;


    public Company(String NIP, String name, String street, String streetNumber,
                   String postCode, String city) {
        this.NIP = NIP;
        this.name = name;

        /**
         * Stworzenie klasy anonimowej w oparciu o interfejs Adress
         */
        this.corespondentAddress = new Adress() {
            String street;
            String streetNumber;
            String postCode;
            String city;

            @Override
            public void setAddress(String street, String streetNumber, String postCode, String city) {
                this.street = street;
                this.streetNumber = streetNumber;
                this.postCode = postCode;
                this.city = city;
            }

            @Override
            public String showAddress() {
                return new StringBuilder().append("\nul. ").append(street).append(" ").append(streetNumber)
                        .append(" ").append(postCode).append(" ").append(city).toString();
            }
        };

        corespondentAddress.setAddress(street, streetNumber, postCode, city);

    }

    /**
     *
     * @return nazwa firm
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return NIP
     */
    public String getNIP() {
        return NIP;
    }

    /**
     *
     * @return sformatowane dane
     */
    @Override
    public String toString() {

        return new StringBuilder().append("Firma:  ").append(name).append(corespondentAddress.showAddress())
                .append("\nNIP: ").append(NIP).append("\n").toString();
    }
}

/**
 * Szkielet adresu, adres powinno sie dac ustawic i wyswietlic
 */
interface Adress {
    void setAddress(String street, String streetNumber, String postCode, String city);

    String showAddress();
}