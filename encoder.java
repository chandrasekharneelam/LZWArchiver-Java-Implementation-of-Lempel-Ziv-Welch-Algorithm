/* 
Name : Jaswanth Yadla
Student ID : 801275432, Sec: 002
Email Id : jyadla@uncc.edu 
*/


import java.io.*;
import java.lang.*;
import java.util.HashMap;

public class encoder {
    public static void main(String[] args) throws IOException {
        double Table_size;
        int t,cs=256;
        Table_size=Math.pow(2,Integer.parseInt(args[1])); // To Compute maximum Table size where bit length (args[1]) is specified. Here it is 12.
        String inputfile,outputfile;
        inputfile = args[0];                              // args[0] is the specified file name in command line.
        outputfile=inputfile.substring(0,6)+".lzw";       // Storing the output file name as specified.
        BufferedWriter opt=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile),"UTF-16BE")); // writes the output file in the specified character set.
        BufferedReader br=new BufferedReader(new FileReader(inputfile)); // File Reader is used for reading stream of characters.
        HashMap<String,Integer> map = new HashMap<>();    // Hashmap is used to store keys and values, here keys are characters and values are integers.
        for(int i=0;i<256;i++)
        {
            map.put(String.valueOf((char)i),i);    // stores the ASCII values from 0-255 in hashmap.
        }
        String initial="",c;
        while((t=br.read())!=-1)                // Reads file until it reaches end of file.
        {
            c= String.valueOf((char)t);
            if (map.containsKey(initial+c))     // looks for initial + c exists in hashmap, if it does exist, we copy that to String initial.
            {
                initial = initial + c;
            }
            else
            {
                System.out.println(map.get(initial));       // It prints the value for the given key.
                opt.write(map.get(initial));                // Writes the value for the given key to opt file.
                if(cs<(int)Table_size)                      // Checks whether current table size is less than maximum table size
                {
                    map.put(initial + c , cs++);            // Stores the keys and values in Hashmap.
                }
                initial = c;
            }
        }
        opt.write(map.get(initial));                        // writes the final value to opt file.
        opt.close();                                        // close the output file.
        br.close();
    }
}
