package com.example.binarypuzzle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author 205117066
 */
public class BinaryPuzzleGenerator {

    /**
     * @param args the command line arguments
     */

    public static int Flag=0;

    public boolean isCorrectSolution(int n, char [][]s)
    {

        for(int i=0;i<n;i++)
        {

            for(int j=0;j<n;j++)
            {
                if(!isValid(s[i][j],i,j,n,s))
                    return false;

            }
        }

        if(!checkEqualInRowAndCol(n,s))
            return false;

        if((!checkUniqueRow(n,s))||(!checkUniqueCol(n,s)))
            return false;

        return true;
    }
    public boolean checkEqualInRowAndCol(int n,char [][]s)
    {


        for(int i=0;i<n;i++)
        {
            int one=0,zero=0;
            for(int j=0;j<n;j++)
            {
                if(s[i][j]=='0')
                    zero++;
                else
                    one++;
            }
            if(zero>n/2||one>n/2)
                return false;
        }
        for(int i=0;i<n;i++)
        {
            int one=0,zero=0;
            for(int j=0;j<n;j++)
            {
                if(s[j][i]=='0')
                    zero++;
                else
                    one++;
            }
            if(zero>n/2||one>n/2)
                return false;
        }
        return true;

    }
    public boolean isValid(char cur,int r,int c,int n,char [][]s)
    {



        if(!checkLeft(r,c,cur,n,s))
            return false;

        if(!checkRight(r,c,cur,n,s))
            return false;

        if(!checkUp(r,c,cur,n,s))
            return false;

        if(!checkDown(r,c,cur,n,s))
            return false;

        return true;

    }
    public void printSudoku(int n,char [][]s)
    {

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
                System.out.print(s[i][j]+" ");

            System.out.println();

        }

    }
    public void fillPuzzle(int n,char s[][],char puzzle[][])
    {

        for(int i=0;i<n;i++)
        {

            for(int j=0;j<n;j++)
                puzzle[i][j]=s[i][j];

        }
    }
    public boolean checkUp(int r,int c,int cur,int n,char [][]s)
    {

        if(r-2>=0&&s[r-2][c]==s[r-1][c]&&s[r-1][c]==cur)
            return false;

        return true;
    }
    public boolean checkLeft(int r,int c,int cur,int n,char [][]s)
    {

        if(c-2>=0&&s[r][c-2]==s[r][c-1]&&s[r][c-1]==cur)
            return false;

        return true;
    }

    public boolean checkDown(int r,int c,int cur,int n,char [][]s)
    {

        if(r+2<n&&s[r+2][c]==s[r+1][c]&&s[r+1][c]==cur)
            return false;

        return true;
    }
    public boolean checkRight(int r,int c,int cur,int n,char [][]s)
    {

        if(c+2<n&&s[r][c+2]==s[r][c+1]&&s[r][c+1]==cur)
            return false;
        return true;
    }
    public boolean checkMidRow(int r,int c,int cur,int n,char [][]s)
    {
        if(r-1>=0&&r+1<n&&s[r-1][c]==s[r+1][c]&&s[r+1][c]==cur)
            return false;
        return true;

    }
    public boolean checkMidCol(int r,int c,int cur,int n,char [][]s)
    {
        if(c-1>=0&&c+1<n&&s[r][c-1]==s[r][c+1]&&s[r][c+1]==cur)
            return false;
        return true;

    }
    public boolean checkEqualInRow(int r,int c,int cur,int n,char [][]s)
    {
        int zero=0,one=0;
        for(int i=0;i<n;i++)
        {
            if(s[r][i]=='0')
                zero++;
            else if(s[r][i]=='1')
                one++;
        }
        if(cur=='0')
            zero++;
        else
            one++;

        if(zero>n/2||one>n/2)
            return false;

        return true;
    }
    public boolean checkEqualInCol(int r,int c,int cur,int n,char [][]s)
    {
        int zero=0,one=0;
        for(int i=0;i<n;i++)
        {
            if(s[i][c]=='0')
                zero++;
            else if(s[i][c]=='1')
                one++;
        }
        if(cur=='0')
            zero++;
        else
            one++;

        if(zero>n/2||one>n/2)
            return false;

        return true;

    }

    public boolean isOk(int i,int r,int c,int n,char [][]s)
    {

        char cur=(char)(i+'0');

        if(!checkLeft(r,c,cur,n,s))
            return false;

        if(!checkRight(r,c,cur,n,s))
            return false;

        if(!checkUp(r,c,cur,n,s))
            return false;

        if(!checkDown(r,c,cur,n,s))
            return false;

        if(!checkMidRow(r,c,cur,n,s))
            return false;

        if(!checkMidCol(r,c,cur,n,s))
            return false;

        if((!checkEqualInRow(r,c,cur,n,s))||(!checkEqualInCol(r,c,cur,n,s)))
            return false;

        return true;

    }
    public boolean checkUniqueRow(int n, char [][]s)
    {

        Set<String> st=new HashSet<String>();
        for(int i=0;i<n;i++)
        {
            String temp="";
            for(int j=0;j<n;j++)
                temp=temp+String.valueOf(s[i][j]);

            st.add(temp);
        }
        if(st.size()!=n)
            return false;
        return true;

    }
    public boolean checkUniqueCol(int n,char [][]s)
    {
        Set<String> st=new HashSet<String>();
        for(int i=0;i<n;i++)
        {
            String temp="";
            for(int j=0;j<n;j++)
                temp=temp+String.valueOf(s[j][i]);
            st.add(temp);

        }
        if(st.size()!=n)
            return false;

        return true;

    }
    public void randomSudokuSelector(int r,int c,int n,char [][]s,int des,char [][] puzzle)
    {
        if(Flag==1000||des==Flag)
            return;
        if(r==n)
        {
            if(checkUniqueRow(n,s)&&checkUniqueCol(n,s))
            {

                Flag+=1;
                if(des==Flag)
                {
                    //printSudoku(n,s);
                    fillPuzzle(n,s,puzzle);
                }
            }
            return;
        }
        if(s[r][c]!=' ')
            randomSudokuSelector(c==(n-1)?r+1:r,(c+1)%n,n,s,des,puzzle);
        else
        {

            for(int i=0;i<=1;i++)
            {

                if(isOk(i,r,c,n,s))
                {
                    s[r][c]=(char)(i+'0');

                    randomSudokuSelector(c==(n-1)?r+1:r,(c+1)%n,n,s,des,puzzle);

                }

            }
            s[r][c]=' ';

        }

    }
    public void checkUniqueSolvable(int r,int c,int n,char  [][]s)
    {
        if(Flag>1)
            return;
        if(r==n)
        {
            if(checkUniqueRow(n,s)&&checkUniqueCol(n,s))
            {
                //Count solutions
                Flag++;
            }
            return;
        }
        if(s[r][c]!=' ')
            checkUniqueSolvable(c==(n-1)?r+1:r,(c+1)%n,n,s);
        else
        {

            for(int i=0;i<=1;i++)
            {

                if(isOk(i,r,c,n,s))
                {
                    s[r][c]=(char)(i+'0');

                    checkUniqueSolvable(c==(n-1)?r+1:r,(c+1)%n,n,s);

                }

            }
            s[r][c]=' ';

        }

    }
    public void generateSolvablePuzzle(int n,char [][]s)
    {

        int vacant=(3*n*n)/4;
        int ct=0;

        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
        while(vacant>0)
        {

            int i=(int)threadRandom.nextInt(0, n);
            int j=(int)threadRandom.nextInt(0, n);

            if(s[i][j]!=' ')
            {
                /*Flag=0;
                char temp=s[i][j];
                s[i][j]=' ';
                checkUniqueSolvable(0,0,n,s);
                if(Flag==1)
                    vacant--;
                else
                    s[i][j]=temp;*/
                s[i][j]=' ';
                vacant--;
            }
        }


    }
    public void solveSudoku(int r,int c,int n,char [][]s)
    {
        //if(Flag)
        // return;
        if(r==n)
        {
            if(checkUniqueRow(n,s)&&checkUniqueCol(n,s))
            {
                printSudoku(n,s);
                Flag=1;
            }
            return;
        }
        if(s[r][c]!=' ')
            solveSudoku(c==(n-1)?r+1:r,(c+1)%n,n,s);
        else
        {

            for(int i=0;i<=1;i++)
            {

                if(isOk(i,r,c,n,s))
                {
                    s[r][c]=(char)(i+'0');

                    solveSudoku(c==(n-1)?r+1:r,(c+1)%n,n,s);

                }

            }
            s[r][c]=' ';

        }

    }

}

