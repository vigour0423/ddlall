package com.ddl.learn.guava.eventbus.listeners;

import com.google.common.eventbus.Subscribe;
import com.ddl.learn.guava.eventbus.events.Apple;
import com.ddl.learn.guava.eventbus.events.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***************************************
 * @author:Alex Wang
 * @Date:2017/10/18
 * 532500648
 ***************************************/
public class FruitEaterListener
{

    private final static Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void eat(Fruit event)
    {
        if (LOGGER.isInfoEnabled())
        {
            LOGGER.info("Fruit eat [{}].", event);
        }
    }

    @Subscribe
    public void eat(Apple event)
    {
        if (LOGGER.isInfoEnabled())
        {
            LOGGER.info("Apple eat [{}].", event);
        }
    }
}
