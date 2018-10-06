package mt.tweets.kmer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TweeterConfig {


    @Value("${oauth.consumerKey}")
    private String consumerKey;

    @Value("${oauth.consumerSecret}")
    private String consumerSecret;

    @Value("${oauth.accessToken}")
    private String accessToekn;

    @Value("${oauth.accessTokenSecret}")
    private String accessTokenSecret;


    @Bean
    public ConfigurationBuilder configurationBuilder() {
        return new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToekn)
                .setOAuthAccessTokenSecret(accessTokenSecret);
    }


    @Bean
    public Twitter twitter() {
        return new TwitterFactory(configurationBuilder().build())
                .getInstance();
    }


}
