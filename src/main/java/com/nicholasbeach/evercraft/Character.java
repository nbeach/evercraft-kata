package com.nicholasbeach.evercraft;

public class Character {
    private String name;
    private Alignment alignment;
    private int hitPoints = 5;
    private int armorClass = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public boolean attack(Character victim, int roll) {
        if(roll < 1 || roll > 20 ) throw new RollOutOfBoundsException("The roll " + roll + " is outside of the allowed range of 1-20");

        if(roll >= victim.getArmorClass()) {
            victim.setHitPoints(victim.getHitPoints() - 1);
            return true;
        }
        return false;
    }
}
