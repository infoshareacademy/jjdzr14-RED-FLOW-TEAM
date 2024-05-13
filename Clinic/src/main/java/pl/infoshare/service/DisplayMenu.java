package pl.infoshare.service;


public class DisplayMenu {

    public static void chooseMenu () {

        System.out.println("Dzien dobry. Wybierz funkcje: \n" +
                "1.Lekarze \n" +
                "2.Pacjenci \n" +
                "3.Admin \n" +
                "0.Zamknij aplikacje.");



    }

    private static void displayMenuDoctors () {

        System.out.println("1. Wyswietl liste lekarzy \n" +
                "2. Dodaj lekarza");

    }

    private static void displayMenuPatients () {

        System.out.println("1. Wyswietl liste pacjentow \n" +
                "2. Dodaj pacjenta");
    }
    private static void displayMenuAdmin () {
        System.out.println("1. Dodaj uzytkownika \n" +
                "2. Usun uzytkownika \n" +
                "3. Dodaj przychodnie");
        Registration.registerUser();



    }
}




