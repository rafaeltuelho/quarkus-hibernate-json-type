package org.acme;

import java.io.Serializable;

public class Power implements Serializable {
  private String name;
  private String tier;
  private int score = 0;
  private String aliases;
  private String description;

  public Power() {
  }

  public Power(String name, String tier, int score, String aliases, String description) {
    this.name = name;
    this.tier = tier;
    this.score = score;
    this.aliases = aliases;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTier() {
    return tier;
  }

  public void setTier(String tier) {
    this.tier = tier;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getAliases() {
    return aliases;
  }

  public void setAliases(String aliases) {
    this.aliases = aliases;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((aliases == null) ? 0 : aliases.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + score;
    result = prime * result + ((tier == null) ? 0 : tier.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Power other = (Power) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "Power [aliases=" + aliases + ", description=" + description + ", name=" + name + ", score=" + score
        + ", tier=" + tier + "]";
  }

}


