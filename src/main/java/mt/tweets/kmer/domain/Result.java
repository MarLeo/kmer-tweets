package mt.tweets.kmer.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor(access = PRIVATE, force = true)
@RequiredArgsConstructor
public class Result {

    private final String name;
    private final String screenName;
    private final String text;
    private final int retweetCount;

}
