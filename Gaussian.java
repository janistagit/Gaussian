import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


class Gaussian
{
    public static void main(String[] args)
    {
        System.out.println("\nGAUSSIAN ELIMINATION WITH SCALED PARTIAL PIVOTING");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number of equations: ");
        int equations = scan.nextInt();
        System.out.println("\nWould you like to:\n1. Enter the coefficients from the command line\n2. Read a file");
        int option = scan.nextInt();

    }

    private Double[] readFile(String fileName) throws IOException
    {
        ArrayList<Double> list = new ArrayList<Double>();
        String[] input = {};
        File myFile = new File(fileName);
        Scanner inputFile = new Scanner(myFile);
        Double[] output = {};

        while(inputFile.hasNextLine())
        {
            String line = inputFile.nextLine();
            input = line.split(" ");

            for(int i = 0; i < input.length; i++)
            {
                list.add(Double.parseDouble(input[i]));
            }
        }

        output = list.toArray(output);

        inputFile.close();
        return output;
    }
}