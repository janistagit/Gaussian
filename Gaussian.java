import java.util.Scanner;
import java.io.File;
import java.io.IOException;


class Gaussian
{
    private static int equations = 11;
    private static int option = 0;
    private static double[][] matrix;
    private static int[] result;
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

        result = new int[equations];

        index = new int[equations];
        for(int i = 1; i <= equations; i++)
        {
            index[i] = i;
        }

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
            }
            kb.close();
        }

        for(int i = 0; i < equations; i++)
            {
                for(int j = 0; j < equations; j++)
                {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.print("\n");
            }

        scan.close();
    }

    private static void Gauss(int n, double[][] matrix, int[]index)
    {
        int i, j = 0, k;
        double r, rmax, smax, xmult;
        double[] scale = new double[n];
        for(i = 0; i < n; i++)
        {
            smax = 0;
            for(j = 0; j < n; j++)
            {
                if(Math.abs(matrix[i][j]) >= smax)
                {
                    smax = Math.max(smax, Math.abs(matrix[i][j]));
                }
            }
            scale[i] = smax;
        }

        for(k = 1; k < n-1; k++)
        {
            rmax = 0;
            for(i = k; i < n; i++)
            {
                r = Math.abs(matrix[index[i]][k]/scale[index[i]]);
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
                    matrix[index[i]][j] = matrix[index[i]][j] - (xmult*matrix[index[k]][j]);
                }
            }
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
}