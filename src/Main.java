
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Scanner;

public class Main{
    public static void main( String[] args){

        int[] price = new int[24];


         Scanner scanner = new Scanner(System.in);
        menu();

            x:
            while (true){
                String choice = scanner.nextLine();

                switch (choice) {

                     case "1":
                        for ( int i = 0; i < price.length; i++) {
                            System.out.println("Skriv el priset (öre per kWh) mellan kl: " + i +"-"+(i+1));
                            price[i]=scanner.nextInt();
                        }
                        break;

                    case "2":
                        int sum = 0;
                        double average;

                        for (int j : price) {
                            sum = sum + j;
                        }
                        average = (double)sum/price.length;

                        IntSummaryStatistics stat = Arrays.stream(price).summaryStatistics();

                            int minPrice = stat.getMin();
                            int maxPrice = stat.getMax();

                            System.out.println("Min = " + minPrice + " öre.");
                            System.out.println("Max = " + maxPrice + " öre.");
                        System.out.println("Medelpriset = " + average + " öre.");
                        break;

                    case "3":

                        Arrays.sort(price);
                        for (int element: price) {
                            System.out.println(element);
                        }
                        break;

                    case "4":
                        Arrays.sort(price);

                        int[] lowestValues = new int[4];
                        Arrays.fill(lowestValues, Integer.MAX_VALUE);

                        for(int n : price) {
                            if(n < lowestValues[3]) {
                                lowestValues[3] = n;
                                Arrays.sort(lowestValues);
                            }
                        }

                        for (int element: lowestValues) {
                            System.out.println(element+" öre.");
                        }
                        break;

                    case "E", "e":
                        break x;

                    default:
                        System.out.println("Vänligen välj ett nummer av menyn!");
                        menu();
                        break;
                   }
               }
            }
            private static void menu(){
                System.out.println("""
                                Elpriser
                                ========
                                1. Inmatning
                                2. Min, Max och Medel
                                3. Sortera
                                4. Bästa Laddningstid (4h)
                                e. Avsluta""");
    }
         }
