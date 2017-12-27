package com.mayank.rss.test;

import java.io.*;

/**
 * Created by mayankgupta on 17/12/17.
 */
public class fileReader {




    public static void main(String[] args) throws IOException {



        File f1 = new File("/Users/mayankgupta/Documents/Zerodha/abc.txt");
        File f2 = new File("/Users/mayankgupta/Documents/Zerodha/ABCOUT.txt");

        System.out.println("f1.length() = " + f1.length());

        /*BufferedWriter w = new BufferedWriter(new FileWriter(f1));

        for(int i=0;i<1000;i++) {
            w.write("This is line jdfdjfhdjf djkfhdjkfhd dfhdjkfhdkjf kjdfhdkjfhdjkf dkjfhdjkfhdjfk kjdhfkdjfh , This is line " + i + "\n");
            w.write("This is line jdfdjfhdjf djkfhdjkfhd dfhdjkfhdkjf kjdfhdkjfhdjkf dkjfhdjkfhdjfk kjdhfkdjfh , This is line " + i + "\n");
            w.write("This is line jdfdjfhdjf djkfhdjkfhd dfhdjkfhdkjf kjdfhdkjfhdjkf dkjfhdjkfhdjfk kjdhfkdjfh , This is line " + i + "\n");
        }

        w.close();*/

        byte[] bytearr = new byte[32*1024];


        OutputStream fo2 = new FileOutputStream(f2);


        //System.out.println("fis2.getChannel().size() = " + fis2.getChannel().size());
        /*fis1.read(bytearr,5,5);
        fis2.skip(5);
        fis2.read(bytearr,0,5);*/
        int times = 3;
        long readby= 0;

        for(int k=1;k<=times;k++) {
            InputStream fis1 = new FileInputStream(f1);
             readby = readby + copy(bytearr, fis1, fo2, f1.length(), times,readby , k);

            fis1.close();
        }


        System.out.println("Output file size = " + f2.length());


        fo2.close();

        for(byte i : bytearr){
           // System.out.println((char)i);
        }

        //Print bytes[]
        for (int i = 0; i < bytearr.length; i++) {
           // System.out.print((char) bytearr[i]);
        }

    }

    private static long copy(byte[] bytearr, InputStream fis1, OutputStream fo2, long lenth, int times, long skip, int index) throws IOException {

        System.out.println("skip = " + skip);
        long limit = lenth/times;
        long loopCounter = limit / (32*1024);
        System.out.println("loopCounter = " + loopCounter);

        long readby = 0;
        int n;

           fis1.skip(skip);

        for(;loopCounter>0;loopCounter--){

             n = fis1.read(bytearr);
             fo2.write(bytearr,0,n);
             readby += n;

         }


        if(index!=times) {
            long leftOverBytes = limit - readby;
            System.out.println("leftOverBytes = " + leftOverBytes);
            n = fis1.read(bytearr, 0, (int) leftOverBytes);
        }else {
            n = fis1.read(bytearr);
            System.out.println("leftOverBytes in last loop = " + n);
        }


        fo2.write(bytearr,0,n);
        readby = readby + n;
        return readby;
    }
}
