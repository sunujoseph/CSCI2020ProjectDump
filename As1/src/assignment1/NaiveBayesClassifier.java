package assignment1;


import java.io.*;
import java.util.*;

public class NaiveBayesClassifier {

    //all unique words that are contained in spam and ham messages will be stored in here.
    public ArrayList<Word> words;

    //the keywords-features that we want to look for in ham messages.
    public HashSet<Word> hamKeywords;

    //the keywords-features that we want to look for in spam messages.
    public HashSet<Word> spamKeywords;

    //P(spam)
    public double spam_probability;

    //P(ham)
    public double ham_probability;

    //total number of ham messages that we checked.
    private int number_of_ham_messages;

    //total number of ling messages that we checked.
    private int number_of_spam_messages;

    //total number of messages that we checked spam+ling.
    private int number_of_messages;




    public NaiveBayesClassifier()
    {
        this.hamKeywords = new HashSet<Word>();
        this.spamKeywords = new HashSet<Word>();
        this.words = new ArrayList<Word>();
        this.spam_probability=0.0;
        this.ham_probability=0.0;
        this.number_of_messages=0;
        this.number_of_ham_messages=0;
        this.number_of_spam_messages=0;
    }








    private void ComputeProbabilities()
    {
        //SUM( N_t,ham ).
        int N_ham = 0;

        //SUM( N_t,spam ).
        int N_spam = 0;

        for(int i = 0; i< words.size(); i++)
        {
            N_ham  += words.get(i).getHamFrequency();
            N_spam +=  words.get(i).getSpamFrequency();
        }


        for(int i = 0; i< words.size(); i++)
        {
            words.get(i).computeHamProbability(words.size() , N_ham);
            words.get(i).computeSpamProbability(words.size() , N_spam);
        }
    }






    //---------------Training Files here---------------//


    public void train(String hamPath , String spamPath, String ham2Path)
    {
        trainHam(hamPath);
        trainHam(ham2Path);
        trainSpam(spamPath);
        number_of_messages=number_of_ham_messages+number_of_spam_messages;
        ham_probability=(double)number_of_ham_messages/(double)number_of_messages;
        spam_probability=(double)number_of_spam_messages/(double)number_of_messages;

        this.ComputeProbabilities();
    }



    //check ham files
    public void trainHam(String hamPath)
    {
        try
        {
            File directory = new File(hamPath);
            File[] files = directory.listFiles();

            number_of_ham_messages=files.length;

            for(File f :files)
            {
                System.out.println("HAM FILE: "+f.getName());
                BufferedReader br = new BufferedReader(new FileReader(f));

                String line = br.readLine();

                while(line!=null)
                {
                    StringTokenizer tok = new StringTokenizer(line);

                    while(tok.hasMoreTokens())
                    {
                        Word w = new Word(tok.nextToken());

                        int pos = words.indexOf(w);

                        if(pos==-1)
                        {
                            w.FoundInHam();
                            words.add(w);
                        }

                        else words.get(pos).FoundInHam();
                    }

                    line=br.readLine();
                }

                br.close();
            }
        }

        catch(Exception e)
        {
            System.err.println("ERROR : "+e.getMessage());
        }
    }



    //check Spam files
    public void trainSpam(String spamPath)
    {
        try
        {
            File directory = new File(spamPath);
            File[] files = directory.listFiles();

            number_of_spam_messages=files.length;

            for(File f :files)
            {
                System.out.println("SPAM FILE: "+f.getName());
                BufferedReader br = new BufferedReader(new FileReader(f));

                String line = br.readLine();

                while(line!=null)
                {
                    StringTokenizer tok = new StringTokenizer(line);

                    while(tok.hasMoreTokens())
                    {
                        Word w = new Word(tok.nextToken());

                        int pos = words.indexOf(w);

                        if(pos==-1)
                        {
                            w.FoundInSpam();
                            words.add(w);
                            spamKeywords.add(w);
                        }

                        else words.get(pos).FoundInSpam();
                        //spamKeywords.
                    }

                    line=br.readLine();
                }

                br.close();
            }
        }

        catch(Exception e)
        {
            System.err.println("ERROR : "+e.getMessage());
        }
    }




}
