package Games;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FortuneCookie {

    private static List<String> fortuneCookieMessages = Arrays.asList("A dream you have will come true",
                        "A good way to keep healthy is to eat more Chinese food",
                        "A journey of a thousand miles begins with a single step",
                        "A new voyage will fill your life with untold memories",
                        "A short stranger will soon enter your life with blessings to share",
                        "A stranger, is a friend you have not spoken to yet",
                        "A thrilling time is in your immediate future",
                        "A very attractive person has a message for you",
                        "About time I got out of that cookie",
                        "Accept your past without regrets. Handle your present with confidence. Face your future without fear",
                        "Adversity is the parent of virtue",
                        "An upward movement initiated in time can counteract fate",
                        "Ask yourself if what you are doing today is getting you closer to where you want to be tomorrow",
                        "Come back later, I am sleeping. Yes cookies need their sleep too",
                        "Could I get some directions? To Where? To your heart",
                        "Everyone agrees. You are the best",
                        "Fortune favors the brave",
                        "Fortune Not Found: Abort, Retry, Ignore?",
                        "Happiness is an activity",
                        "Hearty laughter is a god way to jog internally without having to go outdoors",
                        "If winter comes, can spring be far behind?",
                        "If you feel you are right, stand firmly by your convictions",
                        "If you have something worth fighting for, then fight for it",
                        "It is easier to resist at the beginning than at the end",
                        "It is now, and in this world, that we must live",
                        "Its amazing how much good you can do if you don’t care who gets the credit",
                        "It’s better to be alone sometimes",
                        "Jealousy doesn’t open doors, it closes them",
                        "Joys are often the shadows, cast by sorrows",
                        "Keep your eye out for someone special",
                        "Let the deeds speak",
                        "Love can last a lifetime, if you want it to",
                        "Never give up. Always find a reason to keep trying",
                        "Never give up. You’re not a failure if you don’t give up",
                        "No gift to your mother can ever equal her gift to you – life",
                        "Nothing astonishes men so much as common sense and plain dealing",
                        "Now is the time to try something new",
                        "Now would be a good time to take up a new sport",
                        "Our deeds determine us, as much as we determine our deeds",
                        "Serious trouble will bypass you",
                        "Sometimes you just need to lay on the floor",
                        "Stay true to those who would do the same for you",
                        "Stop wishing. Start doing",
                        "The greatest risk is not taking one");

    public static void main(String[] args) {
        Random random = new Random();
        int index = random.nextInt(fortuneCookieMessages.size());
        System.out.println(fortuneCookieMessages.get(index));
    }


}
