package no.stelar7.api.l4j8.basic.ratelimiting;


import java.time.Instant;
import java.util.*;

/**
 * Burst ratelimiter will use as many calls as possible, then wait when it reaches the limit
 */
public class BurstRateLimiter extends RateLimiter
{
    
    private volatile Map<RateLimit, Instant> firstCallInTime;
    private volatile Map<RateLimit, Long>    callCountInTime;
    
    public BurstRateLimiter(RateLimit... limits)
    {
        super(limits);
        
        firstCallInTime = new HashMap<>();
        callCountInTime = new HashMap<>();
        
        for (RateLimit limit : limits)
        {
            firstCallInTime.put(limit, Instant.now().minusMillis(limit.getTimeframeInMS()));
            callCountInTime.put(limit, 0L);
        }
    }
    
    @Override
    public void acquire()
    {
        try
        {
            semaphore.acquireUninterruptibly();
            
            Thread.sleep(getDelay());
            
            update();
            
            semaphore.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    private long getDelay()
    {
        Instant now   = Instant.now();
        long[]  delay = {0};
        int     bias  = 1;
        
        limits.stream().sorted(Comparator.comparing(RateLimit::getTimeframeInMS)).forEachOrdered(l ->
                                                                                                 {
                                                                                                     if (callCountInTime.get(l) >= l.getRequests())
                                                                                                     {
                                                                                                         long newDelay = firstCallInTime.get(l).toEpochMilli() + l.getTimeframeInMS() - now.toEpochMilli();
                                                                                                         if (newDelay > delay[0])
                                                                                                         {
                                                                                                             delay[0] = newDelay;
                                                                                                         }
                                                                                                     }
                                                                                                 });
        
        if (delay[0] != 0)
        {
            delay[0] = (Math.round(delay[0] / 1000f) + bias) * 1000L;
        }
        
        System.out.println("Sleeping for: " + delay[0]);
        
        return delay[0];
    }
    
    private void update()
    {
        Instant now = Instant.now();
        limits.forEach(l ->
                       {
                           if (firstCallInTime.get(l).toEpochMilli() < now.toEpochMilli() - l.getTimeframeInMS())
                           {
                               firstCallInTime.put(l, now);
                               callCountInTime.put(l, 0L);
                           }
            
                           callCountInTime.compute(l, (k, v) -> v + 1);
            
                           System.out.println("Calls made: " + callCountInTime.get(l) + " in: " + l.getTimeframeInMS() / 1000);
                       });
    }
    
}