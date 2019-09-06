package no.stelar7.api.l4j8.basic.constants.types;

import java.util.*;
import java.util.stream.*;

public enum GameModeType implements CodedEnum
{
    /**
     * Classic Summoner's Rift and Twisted Treeline games
     */
    CLASSIC,
    /**
     * Dominion/Crystal Scar games
     */
    ODIN,
    /**
     * ARAM games
     */
    ARAM,
    /**
     * Tutorial games
     */
    TUTORIAL,
    TUTORIAL_MODULE_1,
    TUTORIAL_MODULE_2,
    TUTORIAL_MODULE_3,
    /**
     * One for All games
     */
    ONEFORALL,
    /**
     * Ascension games
     */
    ASCENSION,
    /**
     * Snowdown Showdown games
     */
    FIRSTBLOOD,
    /**
     * King Poro games
     */
    KINGPORO,
    /**
     * Nexus Siege games
     */
    SIEGE,
    /**
     * Blood Hunt Assassin games
     */
    ASSASSINATE,
    /**
     * All Random Summoner's Rift games
     */
    ARSR,
    /**
     * Darkstar games
     */
    DARKSTAR,
    /**
     * Invasion mode
     */
    STARGUARDIAN,
    /**
     * URF mode
     */
    URF,
    /**
     * Doombots mode
     */
    DOOMBOTSTEEMO,
    /**
     * Overcharge mode
     */
    PROJECT,
    /**
     * Snow Battle ARURF mode
     */
    SNOWURF,
    /**
     * Nexus Blitz mode
     */
    GAMEMODEX,
    /**
     * Odyssey: Extraction mode
     */
    ODYSSEY;
    
    /**
     * Returns a GameModeType from the provided value
     *
     * @param gameMode the mode to check
     * @return GameModeType
     */
    public Optional<GameModeType> getFromCode(final String gameMode)
    {
        return Stream.of(GameModeType.values()).filter(t -> t.name().equalsIgnoreCase(gameMode)).findFirst();
    }
    
    
    /**
     * Used internaly in the api...
     *
     * @return the value
     */
    public String getValue()
    {
        return this.name();
    }
    
    /**
     * The value used to map strings to objects
     *
     * @return String
     */
    public String getCode()
    {
        return this.name();
    }
}
