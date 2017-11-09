package space.samatov.mathmarathon.model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import space.samatov.mathmarathon.model.utils.Formatter;

/**
 * Created by iskenxan on 11/8/17.
 */

public class QuestionGenerator {

    private Random mRandom;
    int mMax;
    int mMin;



    public QuestionGenerator(int min,int max){
        mRandom=new Random();
        mMin=min;
        mMax=max;
    }


    public Question generateRandomQuestion(){
        Question question=new Question();

        int rightAnswer=getQuestionsAndRightAnswer(question);
        setRightAnswer(question,rightAnswer);
        getWrongAnswers(question,rightAnswer);

        return question;
    }



    private int  getQuestionsAndRightAnswer(Question question){
        int firstNum= generateRandomNumberWithinRange();
        int secondNum= generateRandomNumberWithinRange();
        int sign= getRandomWithMax(2);  //0=plus,1=minus,2=multiply
        int rightAnswer=getRightAnswer(firstNum,secondNum,sign);

        String signStr=Formatter.convertSignToStr(sign);
        String questionStr=firstNum+signStr+secondNum+"=?";
        question.setQuestion(questionStr);

        return rightAnswer;
    }



    private void setRightAnswer(Question question,int rightAnswer){
        int rightAnswerIndex=getRandomWithMax(3);
        question.setRightAnswerIndex(rightAnswerIndex);
        question.getAnswers().set(rightAnswerIndex,rightAnswer+"");
    }



    private void getWrongAnswers(Question question,int rightAnswer){
        int i=0;
        while (i<=4){

            String wrongAnswer=getWrongAnswer(rightAnswer);
            if(question.getAnswers().get(i)!=null){
                i++;
            }
            else if(!question.getAnswers().contains(wrongAnswer)){
                question.getAnswers().set(i,wrongAnswer);
                i++;
            }
        }
    }


    private int generateRandomNumberWithinRange(){
        return ThreadLocalRandom.current().nextInt(mMin, mMax + 1);
    }



    private int getRightAnswer(int firstNum,int secondNum, int sign){
        int answer=0;
        switch (sign){
            case 0:{
                answer=firstNum+secondNum;
                break;
            }
            case 1:{
                answer=firstNum-secondNum;
                break;
            }
            case 2:{
                answer=firstNum*secondNum;
            }
        }
        return answer;
    }



    private String getWrongAnswer(int rightAnswer){
        int sign= getRandomWithMax(2);
        int interval= getRandomWithMax(2);
        int answer=0;

        switch (sign){
            case 0:{
                answer= rightAnswer+interval;
                break;
            }
            case 1:{
                answer=rightAnswer-interval;
                break;
            }
            case 2:{
                answer=rightAnswer*interval;
                break;
            }
        }

        return answer+"";
    }


    private int getRandomWithMax(int max){
        return ThreadLocalRandom.current().nextInt(0, max+1);
    }

}
