package com.teamtreehouse.model;

import java.util.*;

public class Team {

    private String name;
    private String coach;

    private int experiencedPlayers;
    private int inexperiencedPlayers;

    private Set<Player> players;
    // if Player implements Comparable
    // implement as TreeSet, because (i) sets have unique elements (ii) TREEset is ordered by the compareTo function,

    public Team(String name, String coach) {
        this.name = name;
        this.coach = coach;
        this.experiencedPlayers = 0;
        this.inexperiencedPlayers = 0;

        this.players = new TreeSet<>();
    }

    public boolean addPlayer(Player player) {
        boolean added = false;
        if (!player.isTaken()) {
            added = this.players.add(player);
            if (added) {
                adjustExperienceWhenPlayerIsAddedToTeam(player);
                player.setTaken(true);
            }
        } else {
            System.out.print("This player is already taken by a team. "); //TODO:rm should be in prompter
        }
        return added;
    }

    // balance increases by 1 when an experienced player joins the team and decreases by 1 when
    // an inexperienced player joins the team
    public void adjustExperienceWhenPlayerIsAddedToTeam(Player player) {
        if (player.isPreviousExperience()) {
            experiencedPlayers++;
        } else {
            inexperiencedPlayers++;
        }
    }

    public void adjustExperienceWhenPlayerIsRemovedFromTeam(Player player) {
        if (player.isPreviousExperience()) {
            experiencedPlayers--;
        } else {
            inexperiencedPlayers--;
        }
    }

    public boolean removePlayer(Player player) {
        boolean removed = this.players.remove(player);
        if (removed) {
            adjustExperienceWhenPlayerIsRemovedFromTeam(player);
            player.setTaken(false);
        }
        return removed;
    }

    public int getExperiencedPlayers() {
        return experiencedPlayers;
    }

    public int getInexperiencedPlayers() {
        return inexperiencedPlayers;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public List<Player> getPlayersByHeight() {
        ArrayList<Player> list = new ArrayList<>(players);
        //TODO: lambda
        // http://stackoverflow.com/questions/22637900/java8-lambdas-vs-anonymous-classes
        Collections.sort(list , new Comparator<Player>() {

            public int compare(Player p1, Player p2) {
                int comp =  p1.getHeightInInches() - (p2.getHeightInInches());
                if (comp > 0) return 1;
                else if (comp < 0) return -1;
                else return 0;
            }
        });
        return list;
    }
}
