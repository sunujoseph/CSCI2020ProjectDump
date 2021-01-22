package assignment1;

public class Word {

    //The word itself.
    private String word;
    //The number of times the word was found in a collection of ham messages.
    private int inHams;
    //The number of times the word was found in a collection of spam messages.
    private int inSpams;
    //P(word|ham)
    private double hamProbability;
    //P(word|spam)
    private double spamProbability;


    public Word(String word)
    {
        this.word=word;
        this.inHams = 0;
        this.inSpams = 0;
        this.hamProbability=0.0;
        this.spamProbability=0.0;
    }




    public void setWord(String word)
    {
        this.word=word;
    }




    public String getWord()
    {
        return this.word;
    }




    public void FoundInHam()
    {
        this.inHams++;
    }




    public int getHamFrequency()
    {
        return this.inHams;
    }




    public void computeHamProbability(int number_of_keywords , int sum)
    {
        //P(t | ling) = [1 + N(t, ling)] / [m + N(ling)]
        this.hamProbability= ( (double)(1+this.inHams)/(double)(number_of_keywords+sum) );
    }






    public void FoundInSpam()
    {
        this.inSpams++;
    }




    public int getSpamFrequency()
    {
        return this.inSpams;
    }




    public void computeSpamProbability(int number_of_keywords , int sum)
    {
        //P(t | spam) = [1 + N(t, spam)] / [m + N(spam)]
        this.spamProbability= ( (double)(1+this.inSpams)/(double)(number_of_keywords+sum) );
    }






    @Override
    public String toString()
    {
        return this.word;
    }




    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass()!=this.getClass()) return false;

        Word w = (Word)obj;

        if(this.word.equalsIgnoreCase(w.word)) return true;

        return false;
    }




    @Override
    public int hashCode()
    {
        return this.word.hashCode();
    }

}
