package func_style.before;

public class QueriedBureau {

	 

    private int[] queriedBureauArray;

    private int[] queriedNumberArray;

    private int size = 0;

   

    public QueriedBureau(int bureauTotalCount){

          size = bureauTotalCount;

          queriedBureauArray = new int[size];

          queriedNumberArray = new int[size];

    }

   

    public int getSize(){

          return size;

    }

    public void setBureauQueried(int bureauNumber){

          if(bureauNumber > 0 && bureauNumber <= queriedBureauArray.length)

                queriedBureauArray[bureauNumber - 1] = 1;

    }

   

    public void setBureauQueriedNumber(int bureauNumber){

          if(bureauNumber > 0 && bureauNumber <= queriedNumberArray.length)

                queriedNumberArray[bureauNumber - 1]++;

    }



    public void setBureauQueried(String bureauNumber){

          int number = new Integer(bureauNumber).intValue();

          setBureauQueried(number);

    }

   

    public void setBureauQueriedNumber(String bureauNumber){

          int number = new Integer(bureauNumber).intValue();

          setBureauQueriedNumber(number);

    }
  


    public String getQueriedBureauString(){

          StringBuffer sb = new StringBuffer();

          for (int i = 0; i < queriedBureauArray.length; i++) {

                sb.append(queriedBureauArray[i]);

          }

          return sb.toString();

    }

   

    public String getQueriedNumberString(){

          StringBuffer sb = new StringBuffer();

          for (int i = 0; i < queriedNumberArray.length; i++) {

                sb.append(queriedNumberArray[i]);

          }

          return sb.toString();

    }

}