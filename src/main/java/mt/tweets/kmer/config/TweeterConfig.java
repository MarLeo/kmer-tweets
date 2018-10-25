package mt.tweets.kmer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class TweeterConfig {


    @Value("${oauth.consumerKey}")
    private String consumerKey;

    @Value("${oauth.consumerSecret}")
    private String consumerSecret;

    @Value("${oauth.accessToken}")
    private String accessToken;

    @Value("${oauth.accessTokenSecret}")
    private String accessTokenSecret;


    @Bean
    public twitter4j.conf.Configuration configuration() {
        return new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret).build();
    }


    @Bean
    public Twitter twitter() {
        return new TwitterFactory(configuration())
                .getInstance();
    }

    @Bean
    public TwitterStream twitterStream() {
        return new TwitterStreamFactory(configuration())
                .getInstance();
    }

    @Bean
    public AsyncTwitter asyncTwitter() {
        return new AsyncTwitterFactory(configuration())
                .getInstance();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
