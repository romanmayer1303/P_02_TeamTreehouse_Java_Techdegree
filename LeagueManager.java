import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        // <teamName, team> , TreeMap sorted alphabetically by keys (name)
        Map<String, Team> teams = new TreeMap<>();
        Prompter prompter = new Prompter();
        int numberOfPlayers = players.length;
        prompter.printNumberOfPlayers(numberOfPlayers);

        int item = 0;

        while (item != 9) {
            prompter.displayMenu();
            item = prompter.getMenuItem();

            if (item == 1) {
                createNewTeam(teams, prompter, numberOfPlayers);
            } else if (item == 2 || item == 3) {
                // add/remove Player to/from team
                addOrRemovePlayer(players, teams, prompter, item);
            } else if (item == 4) {
                listTeamMembersByHeight(teams, prompter);
            } else if (item == 5) {
                printLeagueBalanceReport(teams);
            } else if (item == 6) {
                printRoster(teams, prompter);
            }
        }
    }


    public static void printLeagueBalanceReport(Map<String, Team> teams) {
        System.out.println("League Balance Report: ");
        for (Map.Entry<String, Team> entry : teams.entrySet()) {
            String key = entry.getKey();
            Team team = entry.getValue();
            int numberOfExperiencedPlayers = team.getExperiencedPlayers();
            int numberOfInexperiencedPlayers = team.getInexperiencedPlayers();
            System.out.printf("Team %s has %s experienced and %s inexperienced players. So the average experience" +
                            "is %s.%n",
                    key, numberOfExperiencedPlayers, numberOfInexperiencedPlayers,
                    numberOfExperiencedPlayers + numberOfInexperiencedPlayers);
        }
        System.out.println();
    }

    public static void listTeamMembersByHeight(Map<String, Team> teams, Prompter prompter) {
        String teamName = prompter.getTeamName(teams);
        try {
            Team team = teams.get(teamName);
            List<Player> playersByHeight = team.getPlayersByHeight();
            System.out.println("Players by height: ");
            for (Player player : playersByHeight) {
                System.out.println(player);
            }
        } catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
        }
    }

    public static void addOrRemovePlayer(Player[] players, Map<String, Team> teams, Prompter prompter, int item) {
        // which team?
        String teamName = prompter.getTeamName(teams);

        // list players that are already on the team
        prompter.listTeamMembers(teams, teamName);

        // which player?
        prompter.listAllPlayers(players);
        int playerNumber = prompter.askForPlayerNumber();
        Player player = players[playerNumber - 1];
        Team team = teams.get(teamName);


        if (item == 2) {
            // add
            if (team.addPlayer(player)) {
                System.out.println("Player added successfully.");
            } else {
                System.out.println("Player NOT added.");
            }
        } else if (item == 3) {
            // remove
            if (team.removePlayer(player)) {
                System.out.println("Player removed successfully.");
            } else {
                System.out.println("Player NOT removed.");
            }
        }
    }

    public static void printRoster(Map<String, Team> teams, Prompter prompter) {
        String teamName = prompter.getTeamName(teams);
        prompter.listTeamMembers(teams, teamName);
    }

    public static void createNewTeam(Map<String, Team> teams, Prompter prompter, int numberOfPlayers) {
        if (numberOfPlayers > teams.size()) {
            String[] names;
            names = prompter.readTeamNameAndCoachName();
            String teamName = names[0];
            String coachName = names[1];
            Team team = new Team(teamName, coachName);
            teams.put(teamName, team);
        } else {
            prompter.notEnoughPlayers();
        }
    }
}
