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

    public boolean attack(Character character, int roll) {
        return roll >= character.getArmorClass();
    }
}
