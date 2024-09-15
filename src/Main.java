import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[] price = new int[24];

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                menu();
                String choice = scanner.nextLine().trim().toLowerCase();

                switch (choice) {
                    case "1":
                        inputPrices(price, scanner);
                        break;

                    case "2":
                        displayStatistics(price);
                        break;

                    case "3":
                        sortPrices(price);
                        break;

                    case "4":
                        displayBestChargingTime(price);
                        break;

                    case "e":
                        System.out.println("Avslutar programmet...");
                        return;

                    default:
                        System.out.println("Ogiltigt val, försök igen.");
                        break;
                }
            }
        }
    }

    private static void menu() {
        System.out.println("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """);
    }

    private static void inputPrices(int[] price, Scanner scanner) {
        for (int i = 0; i < price.length; i++) {
            while (true) {
                System.out.println("Skriv el priset (öre per kWh) mellan kl: " + i + "-" + (i + 1));
                try {
                    price[i] = scanner.nextInt();
                    if (price[i] < 0) {
                        System.out.println("Pris kan inte vara negativt. Försök igen.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ogiltig inmatning, försök igen.");
                    scanner.next();
                }
            }
        }
        scanner.nextLine();
    }

    private static void displayStatistics(int[] price) {
        IntSummaryStatistics stat = Arrays.stream(price).summaryStatistics();
        System.out.println("Min = " + stat.getMin() + " öre.");
        System.out.println("Max = " + stat.getMax() + " öre.");
        System.out.println("Medelpriset = " + stat.getAverage() + " öre.");
    }

    private static void sortPrices(int[] price) {
        int[] sortedPrices = Arrays.copyOf(price, price.length);
        Arrays.sort(sortedPrices);
        System.out.println("Sorterade elpriser:");
        for (int element : sortedPrices) {
            System.out.println(element + " öre.");
        }
    }

    private static void displayBestChargingTime(int[] price) {
        if (price.length < 4) {
            System.out.println("Inte tillräckligt med data för att hitta 4h-laddningstid.");
            return;
        }

        Integer[] indices = new Integer[price.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, Comparator.comparingInt(a -> price[a]));

        System.out.println("Bästa laddningstid (lägsta 4 timmar):");
        for (int i = 0; i < 4; i++) {
            int hour = indices[i];
            System.out.println("Timme " + hour + "-" + (hour + 1) + ": " + price[hour] + " öre.");
        }
    }
}
