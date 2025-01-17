package no.stelar7.api.l4j8.basic.serializer;

import com.google.gson.*;
import no.stelar7.api.l4j8.basic.ratelimiting.RateLimit;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class RatelimitDeserializer implements JsonDeserializer<RateLimit>
{
    
    @Override
    public RateLimit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    {
        if (json.isJsonObject())
        {
            int  permit = json.getAsJsonObject().get("permits").getAsInt();
            long delay  = json.getAsJsonObject().get("delayInMs").getAsLong();
            
            return new RateLimit(permit, delay, TimeUnit.MILLISECONDS);
        }
        
        
        if (json.isJsonPrimitive())
        {
            String   data = json.getAsString().split("\\{")[1].split("}")[0];
            String[] objs = data.split(", ");
            
            Object[] result = new Object[2];
            
            for (String obj : objs)
            {
                String field = obj.split("=")[0];
                String value = obj.split("=")[1];
                
                if ("permits".equalsIgnoreCase(field))
                {
                    result[0] = Integer.valueOf(value);
                }
                
                if ("delayInMs".equalsIgnoreCase(field))
                {
                    result[1] = Long.valueOf(value);
                }
            }
            
            return new RateLimit((Integer) result[0], (Long) result[1], TimeUnit.MILLISECONDS);
        }
        
        return null;
    }
}
