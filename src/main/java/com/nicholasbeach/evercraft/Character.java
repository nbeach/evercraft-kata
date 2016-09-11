package com.nicholasbeach.evercraft;

import com.nicholasbeach.evercraft.exception.RollOutOfBoundsException;

public class Character {
    private String name;
    private Alignment alignment;
    private int hitPoints = 5;
    private int armorClass = 10;

    private Ability dexterity;
    private Ability strength;
    private Ability constitution;
    private Ability wisdom;
    private Ability intelligence;
    private Ability charisma;

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
        int modifier =  getConstitution() != null ? getConstitution().getModifier() : 0;
        int modifiedHitPoints = hitPoints + modifier;

        if(modifiedHitPoints >= 1) {
            return modifiedHitPoints;
        } else {
            return 1;
        }
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getArmorClass() {
        int modifier =  dexterity != null ? getDexterity().getModifier() : 0;
        return armorClass + modifier;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public Ability getStrength() {
        return strength;
    }

    public void setStrength(Ability strength) {
        this.strength = strength;
    }

    public Ability getDexterity() {
        return dexterity;
    }

    public void setDexterity(Ability dexterity) {
        this.dexterity = dexterity;
    }

    public Ability getConstitution() {
        return constitution;
    }

    public void setConstitution(Ability constitution) {
        this.constitution = constitution;
    }

    public Ability getWisdom() {
        return wisdom;
    }

    public void setWisdom(Ability wisdom) {
        this.wisdom = wisdom;
    }

    public Ability getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Ability intelligence) {
        this.intelligence = intelligence;
    }

    public Ability getCharisma() {
        return charisma;
    }

    public void setCharisma(Ability charisma) {
        this.charisma = charisma;
    }

    public boolean attack(Character victim, int roll) {
        if(roll < 1 || roll > 20 ) throw new RollOutOfBoundsException("The roll " + roll + " is outside of the allowed range of 1-20");

        if(roll >= victim.getArmorClass()) {
            int damage = roll == 20 ? 2 : 1;
            victim.setHitPoints(victim.getHitPoints() - damage);
            return true;
        }
        return false;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }
}
