package model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CSVReader {

    public SavedData sd;
    InputStream inputStream;

    public CSVReader(InputStream is) {
        this.inputStream = is;
    }

    public List<String[]>  read() throws UnsupportedEncodingException {
        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


        try {
            String csvLine;
            while((csvLine = reader.readLine()) != null) {


                    String[] row = csvLine.split(";;");
                    resultList.add(row);

                    parseLine(row);

            }

        } catch(IOException ex) {
            throw new RuntimeException("Error in reading CSV file:" + ex);
        } finally {
            try{
                inputStream.close();
            } catch(IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }

    //List<String[]>
    public void parseLine(String[] resultList){

        sd=new SavedData();

        String s=resultList[0].toString();

        String [] temp = resultList[0].split(";");

        for(int i=0; i < resultList.length; i++){


           // Log.i("System.out", temp[i]);

        }












    }

}
