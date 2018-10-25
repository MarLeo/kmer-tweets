package mt.tweets.kmer.tweets;

import mt.tweets.kmer.config.TweeterConfig;
import mt.tweets.kmer.util.ApplicationContextProvider;
import twitter4j.*;

public class SearchTweetsStream {

    private static TweeterConfig config = ApplicationContextProvider.getApplicationContext().getBean(TweeterConfig.class);
    private static TwitterStream twitterStream = config.twitterStream();


    public static void getTweets() {
        twitterStream.addListener(getStreamListener()).filter("Etoudi2018");
    }

    private static StatusListener getStreamListener() {
        return new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                System.out.println("Got track limitation notice:" + i);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception e) {

            }
        };
    }


}
