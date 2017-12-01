package no.stelar7.api.l4j8.impl.builders.thirdparty;

import javafx.util.Pair;
import no.stelar7.api.l4j8.basic.calling.DataCallBuilder;
import no.stelar7.api.l4j8.basic.constants.api.*;

public class ThirdPartyCodeBuilder
{
    
    private final Platform platform;
    private final Long     summonerId;
    
    private ThirdPartyCodeBuilder(Platform platform, Long summonerId)
    {
        this.platform = platform;
        this.summonerId = summonerId;
    }
    
    public ThirdPartyCodeBuilder()
    {
        this.platform = Platform.UNKNOWN;
        this.summonerId = null;
    }
    
    public ThirdPartyCodeBuilder withPlatform(Platform platform)
    {
        return new ThirdPartyCodeBuilder(platform, this.summonerId);
    }
    
    public ThirdPartyCodeBuilder withSummonerId(Long id)
    {
        return new ThirdPartyCodeBuilder(this.platform, id);
    }
    
    
    public boolean checkCode()
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.summonerId))
                                                       .withEndpoint(URLEndpoint.V3_THIRD_PARTY_CODE)
                                                       .withPlatform(this.platform);
        
        try
        {
            Pair<String, String> data = (Pair<String, String>) builder.build();
            
            // this is just here to remove intellij warning
            assert data != null;
            
            return false;
        } catch (ClassCastException e)
        {
            e.printStackTrace();
            return true;
        }
    }
    
    public String getCode()
    {
        DataCallBuilder builder = new DataCallBuilder().withURLParameter(Constants.SUMMONER_ID_PLACEHOLDER, String.valueOf(this.summonerId))
                                                       .withEndpoint(URLEndpoint.V3_THIRD_PARTY_CODE)
                                                       .withPlatform(this.platform);
        
        try
        {
            Pair<String, String> data = (Pair<String, String>) builder.build();
            return data.getValue();
        } catch (ClassCastException e)
        {
            e.printStackTrace();
            return (String) builder.build();
        }
    }
}
