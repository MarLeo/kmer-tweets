package mt.tweets.kmer.tweets;

import com.google.appengine.repackaged.org.joda.time.LocalDate;
import mt.tweets.kmer.config.TweeterConfig;
import mt.tweets.kmer.util.ApplicationContextProvider;
import twitter4j.*;

import java.util.List;
import java.util.Map;

import static com.google.appengine.repackaged.com.google.common.collect.Maps.newTreeMap;

public class SearchTweets {

    private static TweeterConfig config = ApplicationContextProvider.getApplicationContext().getBean(TweeterConfig.class);
    private static Twitter twitter = config.twitter();


    public static Map<String, Integer> getTweets(String hashtag) {
//        Map<String, Integer> etoudis = newTreeMap();

        Map<String, Integer> etoudis = newTreeMap();

        Query query = new Query();
        query.setQuery(hashtag);
        query.setLang("fr");
//        query.setSince("20180929");
        query.setCount(100);
        QueryResult result;

        try {
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    User user = tweet.getUser();
                   /* final String name = user.getScreenName();
                    final int retweetCount = tweet.getRetweetCount();*/

                    final String date = LocalDate.fromDateFields(tweet.getCreatedAt()).toString();

                    etoudis.put(date, etoudis.containsKey(date) ? etoudis.get(date) + 1 : 1);

//                    etoudis.put(name, etoudis.containsKey(name) ? etoudis.get(name) + retweetCount : retweetCount);
                }
            } while ((query = result.nextQuery()) != null);


        } catch (TwitterException e) {
            e.printStackTrace();
        }


        return etoudis;
    }


}
