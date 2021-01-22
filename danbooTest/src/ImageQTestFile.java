public class ImageQTestFile {

    public static void main(String args[])
    {
        ImageQ memes = new ImageQ();

        try {
            memes.search("hibiki_(kantai_collection)");
            for(int i = 0; i < memes.getList().size(); i++)
            {
                System.out.println(memes.getList().get(i).getInt("id") + " rating: " + memes.getList().get(i).getString("rating"));
            }
        }
        catch (Exception e)
        {
            System.err.println("Error while searching for Dmitri's shitty waifu: " + e.getMessage() +".");
            System.exit(-1);
        }
    }
}
