package mt.tweets.kmer.tweets;

import mt.tweets.kmer.config.TweeterConfig;
import mt.tweets.kmer.util.ApplicationContextProvider;
import twitter4j.*;

import static twitter4j.TwitterMethod.UPDATE_STATUS;

public class SearchTweetsAsync {

    private static TweeterConfig config = ApplicationContextProvider.getApplicationContext().getBean(TweeterConfig.class);
    private static AsyncTwitter asyncTwitter = config.asyncTwitter();
    private static final Object LOCK = new Object();


    public static void asyncTweets() {
        asyncTwitter.addListener(getTwitterListener());
        asyncTwitter.updateStatus("Etoudi2018");
        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static TwitterAdapter getTwitterListener() {
        return new TwitterAdapter() {
            @Override
            public void updatedStatus(Status status) {
                System.out.println("Successfully updated the status to [" +
                        status.getText() + "].");
                synchronized (LOCK) {
                    LOCK.notify();
                }
            }

            @Override
            public void onException(TwitterException e, TwitterMethod method) {
                if (method == UPDATE_STATUS) {
                    e.printStackTrace();
                    synchronized (LOCK) {
                        LOCK.notify();
                    }
                } else {
                    synchronized (LOCK) {
                        LOCK.notify();
                    }
                    throw new AssertionError("Should not happen");
                }
            }

        };
    }


}
