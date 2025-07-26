/*
 Name : Jaswanth Yadla
Student ID : 801275432, Sec: 002
Email Id : jyadla@uncc.edu 
*/


import java.io.*;
import java.lang.*;
import java.util.*;

public class Decoder {
    public static void main(String[] args) throws IOException {
        int n,i;
        String inputFile, outputFile;
        int table_size =(int)Math.pow(2,Integer.parseInt(args[1])); // table size is used to calculate the maximum table length.
        inputFile=args[0];                                      // args[0] is the output file name of encoder.
        outputFile=inputFile.substring(0,6)+"_decoded.txt";     // stores the outputFile name as "input1_decoded.txt"
        HashMap<Integer,String> map=new HashMap<>();            // Hashmap is used to store keys and values, here keys are integers and values are string type.
        for( i=0;i<256;i++)
        {
            map.put(i,String.valueOf((char)i));                 // stores the ASCII characters from 0 to 255 in hashmap.
        }
        FileInputStream file=new FileInputStream (inputFile); // FileInputStream is used for reading stream of bytes.
        InputStreamReader is =new InputStreamReader(file ,"UTF-16BE"); // Reads the input file as bytes and converts it into characters by using the character set "UTF-16BE"
        BufferedReader b = new BufferedReader(is);
        OutputStreamWriter opt=new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");
        n=b.read();
        String str,newstr;
        str=map.get(n);
        opt.write(str);  // Writes to output file.
        while((n=b.read())!=-1) // Reads until the end of file
        {
            if(!(map.containsKey(n))) // Checks whether key is present in hashmap or not.
            {
                newstr=str+str.charAt(0);
            }
            else
            {
                newstr = map.get(n);
            }
            opt.write(newstr);              // Writes the value to the output file.
            if(i<table_size)
            {
                map.put(i++,str+newstr.charAt(0));          //adds the new key value pair to the hashmap.
            }
            str=newstr;
        }
        opt.close();
        b.close();
    }
}
