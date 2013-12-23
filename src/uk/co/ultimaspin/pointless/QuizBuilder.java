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

            add(newQuestion("Australian Oscar-winning actors")
                    .answer("Nicole Kidman", 58)
                    .answer("Peter Finch", 1)
                    .answer("Heath Ledger", 7)
                    .answer("Cate Blanchett", 8)
                    .answer("Geofforey Rush", 11)
                    .answer("Russel Crowe", 46)
                    .build());

        }};


        return new Quiz(questions);

    }

}
