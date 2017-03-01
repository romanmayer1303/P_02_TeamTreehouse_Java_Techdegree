package com.teamtreehouse.model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
  private static final long serialVersionUID = 1L;

  private String firstName;
  private String lastName;
  private int heightInInches;
  private boolean previousExperience;
  private boolean taken;

  public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.heightInInches = heightInInches;
    this.previousExperience = previousExperience;
    this.taken = false;
  }

  public boolean isTaken() {
    return taken;
  }

  public void setTaken(boolean taken) {
    this.taken = taken;
  }

  public int getHeightInInches() {
    return heightInInches;
  }

  public boolean isPreviousExperience() {
    return previousExperience;
  }

  @Override
  public int compareTo(Player other) {
    // We always want to sort by last name then first name
    int x = lastName.compareTo(other.lastName);
    return x != 0 ? x : firstName.compareTo(firstName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;

    Player player = (Player) o;

    if (heightInInches != player.heightInInches) return false;
    if (previousExperience != player.previousExperience) return false;
    if (!firstName.equals(player.firstName)) return false;
    return lastName.equals(player.lastName);

  }

  @Override
  public int hashCode() {
    int result = firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + heightInInches;
    result = 31 * result + (previousExperience ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Player{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", heightInInches=" + heightInInches +
            ", previousExperience=" + previousExperience +
            '}';
  }
}
