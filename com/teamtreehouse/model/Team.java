package com.teamtreehouse.model;

import java.util.*;

/**
 * Created by romanmayer on 28/02/2017.
 */
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
        boolean added = this.players.add(player);
        if (added) {
            adjustBalanceAdded(player);
        }
        return added;
    }

    // balance increases by 1 when an experienced player joins the team and decreases by 1 when
    // an inexperienced player joins the team
    public void adjustBalanceAdded(Player player) {
        if (player.isPreviousExperience()) {
            experiencedPlayers++;
        } else {
            inexperiencedPlayers++;
        }
    }

    public void adjustBalanceRemoved(Player player) {
        if (player.isPreviousExperience()) {
            experiencedPlayers--;
        } else {
            inexperiencedPlayers--;
        }
    }

    public boolean removePlayer(Player player) {
        boolean removed = this.players.remove(player);
        if (removed) {
            adjustBalanceRemoved(player);
        }
        return removed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }


    public int getExperiencedPlayers() {
        return experiencedPlayers;
    }

    public int getInexperiencedPlayers() {
        return inexperiencedPlayers;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
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
