import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class Prompter {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public int askForPlayerNumber(int numberOfPlayers) {
        try {
            System.out.printf("Select which player you want.%n");
            int input = Integer.parseInt(br.readLine().replaceAll("\\s", ""));
            if (input > 0 && input <= numberOfPlayers) {
                return input;
            } else {
                notValidInput(Integer.toString(input));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return -1;
    }

    public String getTeamName(Map<String, Team> teams) {
        try {
            System.out.println("This is a list of all teams: ");
            for (Map.Entry<String, Team> entry : teams.entrySet()) {
                System.out.println(entry.getKey());
            }
            System.out.println("Select one.");
            String teamName = br.readLine().replaceAll("\\s", "");
            return teamName;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("Not on the list.");
        }
        return null;
    }

    public void listAllPlayers(Player[] players) {
        System.out.println("This is a list of all players: ");
        int i = 1;
        for (Player player : players) {
            System.out.println(i + ": " + player);
            i++;
        }
    }

    public void listTeamMembers(Map<String, Team> teams, String teamName) {
        System.out.printf("These players are on team %s:%n", teamName);
        Team team = teams.get(teamName);
        Iterator it = team.getPlayers().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


    public void displayMenu() {
        System.out.println();
        System.out.println("Press 1 to create a new team.");
        System.out.println("Press 2 to add a player to a team.");
        System.out.println("Press 3 to remove a player from a team.");
        System.out.println("Press 4 to list a team's players by height.");
        System.out.println("Press 5 to get a League Balance Report.");
        System.out.println("Press 9 to exit.");
    }

    public String[] readTeamNameAndCoachName() {
        String[] names = new String[2];
        try {
            System.out.print("Enter team name: ");
            String teamName = br.readLine().replaceAll("\\s", "");
            System.out.print("Enter coach's name: ");
            String coachName = br.readLine().replaceAll("\\s", "");
            names[0] = teamName;
            names[1] = coachName;
            return names;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    public int getMenuItem() {
        int item = -1;
        try {
            item = Integer.parseInt(br.readLine().replaceAll("\\s", ""));
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return item;
    }

    public void printNumberOfPlayers(int numberOfPlayers) {
        System.out.printf("There are currently %d registered players.%n", numberOfPlayers);
    }

    public void notEnoughPlayers() {
        System.out.println("There are already as many teams as there are players. Can't create more teams.");
    }

    public void notValidInput(String item) {
        System.out.printf("'%s' is not a valid input.%n%n", item);
    }
}
