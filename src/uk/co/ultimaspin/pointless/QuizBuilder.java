package uk.co.ultimaspin.pointless;

import uk.co.ultimaspin.pointless.quiz.Question;
import uk.co.ultimaspin.pointless.quiz.QuestionBuilder;

import java.util.ArrayList;
import java.util.List;

import static uk.co.ultimaspin.pointless.quiz.QuestionBuilder.newQuestion;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 23/12/2013
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class QuizBuilder {


    public Quiz sampleQuiz() {

        List<Question> questions = new ArrayList<Question>() {{

            add(newQuestion("Australian Oscar-winning actors").answer("Nicole Kidman", 58)
                    .answer("Peter Finch", 1)
                    .answer("Heath Ledger", 7)
                    .answer("Cate Blanchett", 8)
                    .answer("Gefforey Rush", 11)
                    .answer("Russell Crowe", 46)
                    .build());


            add(newQuestion("Boxers Fought Professionally By Muhammed Ali").answer("Leon Spinks", 4)
                    .answer("Sonny Liston", 15)
                    .answer("Joe Bugner", 1)
                    .answer("Ken Noughton", 3)
                    .answer("Chuck Weptner", 0)
                    .answer("Alvin Lewis", 0)
                    .answer("Trevor Berbick", 0)
                    .answer("Zora Folley", 0)
                    .answer("Sonny Banks", 0)
                    .answer("Don Warner", 0)
                    .answer("Earnie Shavers", 0)
                    .answer("Billy Daniels", 0)
                    .answer("Lamar Clark", 0)
                    .build());

            add(newQuestion("US States ending in 'A'").answer("Alaska", 40)
                    .answer("South Carolina", 10)
                    .answer("North Carolina", 12)
                    .answer("Pennsylvania", 3)
                    .answer("West Virginia", 5)
                    .answer("North Dakota", 8)
                    .answer("Iowa", 8)
                    .answer("California", 62)
                    .answer("South Dakota", 3)
                    .answer("Nebraska", 3)
                    .answer("Alabama", 43)
                    .build());

            add(newQuestion("Cities That Have Held The Fifa World Cup Final").answer("Montevideo", 1)
                    .answer("Johannesburg", 9)
                    .answer("London", 57)
                    .answer("Yokohama", 0)
                    .answer("Solna", 0)
                    .answer("Santiago", 0)
                    .answer("Pasadena", 1)
                    .answer("Bern", 1)
                    .answer("Buenos Aires", 7)
                    .answer("Rio de Janeiro", 11)
                    .answer("Mexico City", 11)
                    .answer("Berlin", 14)
                    .answer("Munich", 14)
                    .answer("Madrid", 18)
                    .answer("Rome", 22)
                    .answer("Paris", 29)
                    .build());

            add(newQuestion("Papel Names Taken By 5 Or More Popes").answer("Pius", 0)
                    .answer("John", 52)
                    .answer("Urban", 0)
                    .answer("Eugene", 0)
                    .answer("Celestine", 0)
                    .answer("Alexander", 1)
                    .answer("Boniface", 2)
                    .answer("Sixtus", 2)
                    .answer("Nicholas", 2)
                    .answer("Clement", 3)
                    .answer("Leo", 4)
                    .answer("Innocent", 4)
                    .answer("Adrian", 4)
                    .answer("Stephen", 7)
                    .answer("Gregory", 11)
                    .answer("Benedict", 34)
                    .answer("Paul", 70)
                    .build());

            add(newQuestion("Beatles top 40 single with 3-word titles").answer("Love Me Do", 31)
                    .answer("I feel fine", 4)
                    .answer("Ain't she sweet", 0)
                    .answer("Baby It's You", 0)
                    .answer("Strawberry Fields Forever", 8)
                    .answer("Please please me", 10)
                    .build());


            add(newQuestion("Cher film (anything in which 'Cher' has had an acting or voice credit - not when she has played herself").answer("Moonstruck", 12)
                    .answer("Mask", 24)
                    .answer("Silkwood", 7)
                    .answer("Suspect", 1)
                    .answer("Faithful", 1)
                    .answer("Chasity", 2)
                    .answer("Burlesque", 18)
                    .answer("Mermaids", 23)
                    .answer("Tea with Mussolini", 3)
                    .answer("Come Back to the Five and Dime, Jimmy Dean, Jimmy Dean!", 0)
                    .answer("Witches of Eastwick", 17)
                    .build());

            add(newQuestion("Capital cities of Canadian provinces and territories").answer("Halifax", 5)
                    .answer("Quebec", 35)
                    .answer("Edmunton", 5)
                    .answer("Whitehorse", 0)
                    .answer("Iqaluit", 0)
                    .answer("Yellowknife", 0)
                    .answer("Vicoria", 0)
                    .build());


        }};


        return new Quiz(questions);

    }

}
