package no.stelar7.api.l4j8.impl.builders.match;

import no.stelar7.api.l4j8.basic.calling.*;
import no.stelar7.api.l4j8.basic.constants.api.*;
import no.stelar7.api.l4j8.basic.utils.Pair;
import no.stelar7.api.l4j8.pojo.match.Match;

import java.util.Optional;

public class MatchBuilder
{
    
    private final long     id;
    private final Platform platform;
    
    public MatchBuilder()
    {
        this.id = -1;
        this.platform = Platform.UNKNOWN;
    }
    
    private MatchBuilder(Platform platform, long id)
    {
        this.id = id;
        this.platform = platform;
    }
    
    public MatchBuilder withId(long id)
    {
        return new MatchBuilder(this.platform, id);
    }
    
    public MatchBuilder withPlatform(Platform platform)
    {
        return new MatchBuilder(platform, this.id);
    }
    
    /**
     * Gets a match based on the parameters passed to the builder.
     * Matches older than 3 years will return null
     *
     * @return Match
     */
    public Match get()
    {
        if (this.id < 0 || this.platform == Platform.UNKNOWN)
        {
            return null;
        }
        
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.MATCH_ID_PLACEHOLDER, String.valueOf(this.id))
                                                       .withEndpoint(URLEndpoint.V3_MATCH)
                                                       .withPlatform(this.platform);
        
        
        Optional chl = DataCall.getCacheProvider().get(URLEndpoint.V3_MATCH, this.platform, this.id);
        if (chl.isPresent())
        {
            Match m = (Match) chl.get();
            if (m.getParticipants().stream().noneMatch(p -> (p.getSpell1() == null || p.getSpell2() == null)))
            {
                return m;
            }
        }
        
        Object matchObj = builder.build();
        if (matchObj instanceof Pair)
        {
            return null;
        }
        
        Match match = (Match) matchObj;
        DataCall.getCacheProvider().store(URLEndpoint.V3_MATCH, match, this.platform, this.id);
        return match;
    }
}
