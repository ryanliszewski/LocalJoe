package com.singhliszewski.eventbrowser;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ryanliszewski on 11/30/16.
 */

class StoryLab {

    private static StoryLab sStoryLab;

    private List<Story> mStories = new ArrayList<Story>();

    public static StoryLab get(Context context) {
        if (sStoryLab == null) {
            sStoryLab = new StoryLab(context);
        }
        return sStoryLab;
    }

    public StoryLab(Context context) {

        for (int i = 0; i < 100; i++) {
            mStories.add(new Story("The Prince","The Prince is a 16th-century political treatise, by the Italian diplomat and political theorist, Niccolò Machiavelli. From correspondence a version appears to have been distributed in 1513, using a Latin title, De Principatibus."));
            mStories.add(new Story("To Kill a Mockingbird", "To Kill a Mockingbird is a novel by Harper Lee published in 1960. It was immediately successful, winning the Pulitzer Prize, and has become a classic of modern American literature."));
            mStories.add(new Story("A Song of Ice and Fire", "A Song of Ice and Fire is a series of epic fantasy novels by the American novelist and screenwriter George R. R. Martin. He began the first volume of the series, A Game of Thrones, in 1991 and had it published in 1996."));
            mStories.add(new Story("Of Mice and Men", "Of Mice and Men is a novella written by author John Steinbeck. Published in 1937, it tells the story of George Milton and Lennie Small, two displaced migrant ranch workers, who move from place to place ..."));
            mStories.add(new Story("The Great Gatsby", "The Great Gatsby is a 1925 novel written by American author F. Scott Fitzgerald that follows a cast of characters living in the fictional town of West Egg on prosperous Long Island in the summer of 1922."));
            mStories.add(new Story("The Scarlet Letter", "The Scarlet Letter: A Romance is an 1850 work of fiction in a historical setting, written by American author Nathaniel Hawthorne. The book is considered to be his \"masterwork\"."));
            mStories.add(new Story("Lord of the Flies", "Lord of the Flies is a 1954 novel by Nobel Prize-winning English author William Golding. The book's premise focuses on a group of British boys stranded on an uninhabited island and their attempt to govern themselves, with disastrous results."));
            mStories.add(new Story("Gone with the Wind", "Epic Civil War drama focuses on the life of petulant southern belle Scarlett O'Hara (Vivien Leigh). Starting with her idyllic on a sprawling plantation, the film traces her survival through the tragic history of the South during the Civil War and Reconstruction, and her tangled love affairs with Ash…"));
            mStories.add(new Story("The Hobbit", "The Hobbit, or There and Back Again is a children's fantasy novel by English author J. R. R. Tolkien."));
            mStories.add(new Story("The Adventures of Huckleberry Finn", "Adventures of Huckleberry Finn is a novel by Mark Twain, first published in the United Kingdom in December 1884 and in the United States in February 1885."));

        }
    }

    public List<Story> getStories() {
        return mStories;
    }

    public Story getStory(UUID id) {
        for (Story story : mStories) {
            if (story.getUUID().equals(id)) {
                return story;
            }
        }
        return null;
    }

}