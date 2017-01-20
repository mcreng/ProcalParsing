package org.bychan.core.dynamic;




import java.util.ArrayList;
import java.util.List;

/**
 * Created by alext on 2015-03-25.
 */
public class ChainedMatcher implements TokenMatcher {
    private final List<TokenMatcher> matchers;

    public ChainedMatcher(List<TokenMatcher> matchers) {
        this.matchers = matchers;
    }


    @Override
    public TokenMatchResult tryMatch( String input, int searchStart) {
        int searchPosition = searchStart;
        ArrayList<String> matches = new ArrayList<>();
        for (TokenMatcher matcher : matchers) {
            TokenMatchResult match = matcher.tryMatch(input, searchPosition);
            if (match == null) {
                return null;
            }
            matches.add(input.substring(searchPosition, match.getMatchEndPosition()));
            searchPosition = match.getMatchEndPosition();
        }
        return TokenMatchResult.create(matches, searchPosition);
    }
}
