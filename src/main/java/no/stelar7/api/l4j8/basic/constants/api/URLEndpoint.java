package no.stelar7.api.l4j8.basic.constants.api;

import com.google.gson.reflect.TypeToken;
import no.stelar7.api.l4j8.pojo.champion.*;
import no.stelar7.api.l4j8.pojo.championmastery.ChampionMastery;
import no.stelar7.api.l4j8.pojo.currentgame.CurrentGameInfo;
import no.stelar7.api.l4j8.pojo.featuredgames.FeaturedGames;
import no.stelar7.api.l4j8.pojo.staticdata.champion.*;
import no.stelar7.api.l4j8.pojo.staticdata.item.*;
import no.stelar7.api.l4j8.pojo.status.ShardStatus;
import no.stelar7.api.l4j8.pojo.summoner.Summoner;
import no.stelar7.api.l4j8.pojo.summoner.masteries.MasteryPages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;

import java.util.*;

public enum URLEndpoint
{
    // lol/summoner/v3/summoners/by-account/{accountId}
    // lol/summoner/v3/summoners/by-name/{summonerName}
    // lol/summoner/v3/summoners/{summonerId}
    V3_SUMMONER_BY_ACCOUNT("lol", "summoner", "v3", "summoners/by-account/" + Constants.ACCOUNT_ID_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_NAME("lol", "summoner", "v3", "summoners/by-name/" + Constants.SUMMONER_NAME_PLACEHOLDER, Summoner.class),
    V3_SUMMONER_BY_ID("lol", "summoner", "v3", "summoners/" + Constants.SUMMONER_ID_PLACEHOLDER, Summoner.class),
    
    // lol/platform/v3/runes/by-summoner/{summonerId}
    // lol/platform/v3/masteries/by-summoner/{summonerId}
    // typetoken should be removed once riot removes the map structure from the JSON
    V3_RUNES_BY_ID("lol", "platform", "v3", "runes/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<Map<Long, RunePages>>()
    {}.getType()),
    V3_MASTERIES_BY_ID("lol", "platform", "v3", "masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<Map<Long, MasteryPages>>()
    {}.getType()),
    
    // lol/spectator/v3/featured-games
    // lol/spectator/v3/active-games/by-summoner/{summonerId}
    V3_SPECTATOR_FEATURED("lol", "spectator", "v3", "featured-games", FeaturedGames.class),
    V3_SPECTATOR_CURRENT("lol", "spectator", "v3", "active-games/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, CurrentGameInfo.class),
    
    // lol/champion-mastery/v3/champion-masteries/by-summoner/{summonerId}
    // lol/champion-mastery/v3/champion-masteries/by-summoner/{summonerId}/by-champion/{championId}
    // lol/champion-mastery/v3/scores/by-summoner/{summonerId}
    V3_MASTERY_BY_ID("lol", "champion-mastery", "v3", "champion-masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, new TypeToken<List<ChampionMastery>>()
    {}.getType()),
    V3_MASTERY_BY_CHAMPION("lol", "champion-mastery", "v3", "champion-masteries/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER + "/by-champion/" + Constants.CHAMPION_ID_PLACEHOLDER, ChampionMastery.class),
    V3_MASTERY_SCORE("lol", "champion-mastery", "v3", "scores/by-summoner/" + Constants.SUMMONER_ID_PLACEHOLDER, Integer.class),
    
    // lol/platform/v3/champions
    // lol/platform/v3/champions/{id}
    V3_CHAMPIONS("lol", "platform", "v3", "champions", ChampionList.class),
    V3_CHAMPIONS_BY_ID("lol", "platform", "v3", "champions/" + Constants.ID_PLACEHOLDER, Champion.class),
    
    // lol/status/v3/shard-data
    V3_SHARD_STATUS("lol", "status", "v3", "shard-data", ShardStatus.class),
    
    // lol/static-data/v3/champions
    // lol/static-data/v3/champions/{id}
    // lol/static-data/v3/items
    // lol/static-data/v3/items/{id}
    V3_STATIC_CHAMPIONS("lol", "static-data", "v3", "champions", StaticChampionList.class),
    V3_STATIC_CHAMPION_BY_ID("lol", "static-data", "v3", "champions/" + Constants.ID_PLACEHOLDER, StaticChampion.class),
    V3_STATIC_ITEMS("lol", "static-data", "v3", "items", ItemList.class),
    V3_STATIC_ITEM_BY_ID("lol", "static-data", "v3", "items/" + Constants.ID_PLACEHOLDER, Item.class),;
    
    
    private final String game;
    private final String service;
    private final String version;
    private final String resource;
    private final Object type;
    
    
    URLEndpoint(String game, String service, String version, String resource, Object type)
    {
        this.game = game;
        this.service = service;
        this.version = version;
        this.resource = resource;
        this.type = type;
    }
    
    public String getGame()
    {
        return game;
    }
    
    public String getService()
    {
        return service;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public String getResource()
    {
        return resource;
    }
    
    public Object getType()
    {
        return type;
    }
}
