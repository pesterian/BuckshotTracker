
package buckshot;
import java.util.ArrayList;
import java.util.Scanner;

public class BuckshotRoulette {
    
    public static class Shell {
        public enum ShellState { LIVE, BLANK, EMPTY }
        private ShellState state;
        public Shell() { this.state = ShellState.EMPTY; }
        public ShellState getState() { return state; }
        public void setState(ShellState state) { this.state = state; }
    }

    public static void main(String[] args) {
        ArrayList<Shell> liveShells = new ArrayList<>(4);
        ArrayList<Shell> blankShells = new ArrayList<>(4);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the number of live shells (0-4): ");
            int numLiveShells = scanner.nextInt();
            System.out.print("Enter the number of blank shells (0-4): ");
            int numBlankShells = scanner.nextInt();
            scanner.nextLine(); 

            if (numLiveShells < 0 || numLiveShells > 4 || numBlankShells < 0 || numBlankShells > 4) {
                System.out.println("Invalid input. Please enter values between 0 and 4.");
                continue;
            }

            liveShells.clear();
            blankShells.clear();
            for (int i = 0; i < numLiveShells; i++) { liveShells.add(new Shell()); liveShells.get(i).setState(Shell.ShellState.LIVE); }
            for (int i = 0; i < numBlankShells; i++) { blankShells.add(new Shell()); blankShells.get(i).setState(Shell.ShellState.BLANK); }

            String note = "";
            while (true) {
                int totalLive = liveShells.size();
                int totalBlank = blankShells.size();
                System.out.println("Live shells: " + totalLive);
                System.out.println("Blank shells: " + totalBlank);
                if (!note.isEmpty()) System.out.println("Note: " + note);
                if (totalLive == 0 && totalBlank == 0) {
                    System.out.println("All shells are empty. Enter new values.");
                    break;
                }
                System.out.print("Enter 'L' to decrement a live shell, 'B' to decrement a blank shell, 'T' to write a note, 'N' to enter new values, or 'E' to exit: ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("L") && totalLive > 0) liveShells.remove(totalLive - 1);
                else if (choice.equalsIgnoreCase("B") && totalBlank > 0) blankShells.remove(totalBlank - 1);
                else if (choice.equalsIgnoreCase("T")) {
                    System.out.print("Enter your note: ");
                    note = scanner.nextLine();
                } else if (choice.equalsIgnoreCase("N")) break;
                else if (choice.equalsIgnoreCase("E")) {
                    System.out.println("Exiting the program.");
                    System.exit(0);
                } else System.out.println("Invalid choice or no shells left to decrement.");
            }
        }
    }
}
