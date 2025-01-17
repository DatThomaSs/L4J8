package no.stelar7.api.l4j8.impl.builders.spectator;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.Pair;
import no.stelar7.api.l4j8.pojo.spectator.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class SpectatorBuilder
{
    private final Platform platform;
    private final String   summonerId;
    
    private SpectatorBuilder(Platform platform, String summonerId)
    {
        this.platform = platform;
        this.summonerId = summonerId;
    }
    
    public SpectatorBuilder()
    {
        this.platform = Platform.UNKNOWN;
        this.summonerId = null;
    }
    
    public SpectatorBuilder withPlatform(Platform platform)
    {
        return new SpectatorBuilder(platform, this.summonerId);
    }
    
    public SpectatorBuilder withSummonerId(String id)
    {
        return new SpectatorBuilder(this.platform, id);
    }
    
    public List<SpectatorGameInfo> getFeaturedGames()
    {
        if (this.platform == Platform.UNKNOWN)
        {
            return Collections.emptyList();
        }
        
        DataCallBuilder builder = new DataCallBuilder().withEndpoint(URLEndpoint.V3_SPECTATOR_FEATURED)
                                                       .withPlatform(this.platform);
        
        try
        {
            Object data = builder.build();
            if (data instanceof Pair)
            {
                return Collections.emptyList();
            }
            
            FeaturedGames fg = (FeaturedGames) data;
            return fg.getGameList();
        } catch (ClassCastException e)
        {
            return Collections.emptyList();
        }
    }
    
    public SpectatorGameInfo getCurrentGame()
    {
        if (this.platform == Platform.UNKNOWN || this.summonerId == null)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.summonerId))
                                                       .withEndpoint(URLEndpoint.V3_SPECTATOR_CURRENT)
                                                       .withPlatform(this.platform);
        
        try
        {
            SpectatorGameInfo fg = (SpectatorGameInfo) builder.build();
            return fg;
        } catch (ClassCastException e)
        {
            return null;
        }
    }
}
