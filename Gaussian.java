import java.util.Scanner;
import java.io.File;
import java.io.IOException;


class Gaussian
{
    private static int equations = 11;
    private static int option = 0;
    private static int[][] matrix = new int[1][1];

    public static void main(String[] args)
    {
        System.out.println("\nGAUSSIAN ELIMINATION WITH SCALED PARTIAL PIVOTING");
        Scanner scan = new Scanner(System.in);
        while(equations>10)
        {
            System.out.println("Please enter the number of equations less than or equal to 10: ");
            equations = scan.nextInt();
        }
        while(option != 1 && option != 2)
        {
            System.out.println("\nWould you like to:\n1. Enter the coefficients from the command line\n2. Read a file");
            option = scan.nextInt();
        }

        if(option == 1)
        {
            System.out.print("\n");
            matrix = new int[equations][4];
            for(int i = 0; i < equations; i++)
            {
                for(int j = 0; j < 4; j++)
                {
                    System.out.println("Enter matrix coefficient: ");
                    matrix[i][j] = scan.nextInt();
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
                for(int j = 0; j < 4; j++)
                {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.print("\n");
            }

        scan.close();
    }

    private static int[][] readFile(String fileName) throws IOException
    {
        File myFile = new File(fileName);
        Scanner inputFile = new Scanner(myFile);
        int[][] output = new int[equations][4];

        for(int i = 0; i < equations; i++)
            {
                for(int j = 0; j < 4; j++)
                {
                    output[i][j] = inputFile.nextInt();
                }
            }

        inputFile.close();
        return output;
    }
}