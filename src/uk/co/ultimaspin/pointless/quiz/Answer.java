package uk.co.ultimaspin.pointless.quiz;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 23/12/2013
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class Answer {

    private String answer;
    private int score;

    public Answer(String answer, int score) {
        this.answer = answer;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.answer + " (" + score + ")";
    }

    public int getScore() {
        return this.score;
    }




}
