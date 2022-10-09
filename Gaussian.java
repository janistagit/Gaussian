/*
 * Project 1
 * By Janista Gitbumrungsin
 * CS3010.02 Fall 2022
 * Dr. Lajpat Rai Raheja
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;


class Gaussian
{
    private static int equations = 11;
    private static int option = 0;
    private static double[][] matrix;
    private static double[] sumArray;
    private static double[] result;
    private static int[] index = {};

    public static void main(String[] args)
    {
        System.out.println("\nGAUSSIAN ELIMINATION WITH SCALED PARTIAL PIVOTING");
        Scanner scan = new Scanner(System.in);
        while(equations > 10)
        {
            System.out.println("Please enter the number of equations less than or equal to 10: ");
            equations = scan.nextInt();
        }
        while(option != 1 && option != 2)
        {
            System.out.println("\nWould you like to:\n1. Enter the coefficients from the command line\n2. Read a file");
            option = scan.nextInt();
        }

        result = new double[equations];
        index = new int[equations];
        sumArray = new double[equations];

        if(option == 1)
        {
            System.out.print("\n");
            matrix = new double[equations][equations];
            for(int i = 0; i < equations; i++)
            {
                for(int j = 0; j < equations+1; j++)
                {
                    if(j < equations)
                    {
                        System.out.println("Enter matrix coefficient: ");
                        matrix[i][j] = scan.nextInt();
                    }
                    else
                    {
                        System.out.println("Enter b value: ");
                        result[i] = scan.nextInt();
                    }
                    
                }
            }
        }
        else if(option == 2)
        {
            Scanner kb = new Scanner(System.in);
            System.out.println("\nEnter the name of the file: ");
            String fileName = kb.nextLine();
            
            try
            {
                matrix = readFile(fileName); 
            }
            catch(IOException exception)
            {
                System.out.println("Error with file reading.");
                System.exit(0);
            }
            kb.close();
        }
        scan.close();

        System.out.println();
        System.out.println("Entered matrix: ");
        printMatrix();
        gauss(equations, matrix, index, result);
        backSub(equations, matrix, index, result, sumArray);
        printAnswer(sumArray);
    }

    private static void gauss(int n, double[][] matrix, int[]index, double[] result)
    {
        int i, j = 0, k;
        double r, rmax, smax, xmult;
        double[] scale = new double[n];

        for(i = 0; i < n; i++)
        {
            index[i] = i;
            smax = 0;
            for(j = 0; j < n; j++)
            {
                smax = Math.max(smax, Math.abs(matrix[i][j]));
            }
            scale[i] = smax;
        }

        for(k = 0; k < n-1; k++)
        {
            rmax = 0;
            for(i = k; i < n; i++)
            {
                r = Math.abs(matrix[index[i]][k] / scale[index[i]]);
                if(r > rmax)
                {
                    rmax = r;
                    j = i;
                }
            }
            int temp = index [j];
            index[j] = index[k];
            index[k] = temp;
            for(i = k+1; i < n; i++)
            {
                xmult = matrix[index[i]][k] / matrix[index[k]][k];
                matrix[index[i]][k] = xmult;
                for(j = k+1; j < n; j++)
                {
                    matrix[index[i]][j] = matrix[index[i]][j] - (xmult*(matrix[index[k]][j]));
                }
            }

            System.out.println();
            System.out.println("Ratio: " + rmax);
            System.out.println("Pivot Index: " + (index[k]+1));
            printMatrix();
        }

    }

    private static void backSub(int n, double[][] matrix, int[] index, double[] result, double[] sumArray)
    {
        int i, k;
        double sum;

        for(k = 1; k < n-1; k++)
        {
            for(i = k+1; i < n; i++)
            {
                result[index[i]] = result[index[i]] - (matrix[index[i]][k] * result[index[k]]);
            }
        }
        sumArray[n-1] = result[index[n-1]] / matrix[index[n-1]][n-1];

        for(i = n-1; i >= 0; i--)
        {
            sum = result[index[i]];
            for(int j = i+1; j < n; j++)
            {
                sum = sum - (matrix[index[i]][j] * sumArray[j]);
            }
            sumArray[i] = sum / matrix[index[i]][i];
        }
    }

    private static double[][] readFile(String fileName) throws IOException
    {
        File myFile = new File(fileName);
        Scanner inputFile = new Scanner(myFile);
        double[][] output = new double[equations][equations];

        for(int i = 0; i < equations; i++)
        {
            for(int j = 0; j < equations+1; j++)
            {
                if(j < equations)
                {
                    output[i][j] = inputFile.nextInt();
                }
                else
                {
                    result[i] = inputFile.nextInt();
                }
            }
        }

        inputFile.close();
        return output;
    }

    public static void printMatrix()
    {
        for(int i = 0; i < equations; i++)
        {
            for(int j = 0; j < equations+1; j++)
            {
                if(j < equations)
                {
                    System.out.print(matrix[i][j] + " ");
                }
                else
                {
                    System.out.print(result[i] + " ");
                }
            }
            System.out.print("\n");
        }

    }

    public static void printAnswer(double[] array)
    {
        System.out.println();
        System.out.println("Solution: ");
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(alphabet.values()[i] + " = " + array[i]);
        }
    }

    public enum alphabet
    {
        x, y, z, a, b, c, d, e, f, g
    }
}