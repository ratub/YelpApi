package com.ratus.yelpapi;

/**
 * This class is used primarily for API keys and secrets to create a central repository.
 * This is useful for having one place to manage several API keys.
 *
 * @author ograycoding.wordpress.com
 */
public class YelpConfig {

    private final String YELP_CONSUMER_KEY ="key";
    private final String YELP_CONSUMER_SECRET = "secret";
    private final String YELP_TOKEN = "token";
    private final String YELP_TOKEN_SECRET = "secret";


    public String getYelpConsumerKey(){
        return YELP_CONSUMER_KEY;
    }

    public String getYelpConsumerSecret(){
        return YELP_CONSUMER_SECRET;
    }

    public String getYelpToken(){
        return YELP_TOKEN;
    }

    public String getYelpTokenSecret(){
        return YELP_TOKEN_SECRET;
    }

}