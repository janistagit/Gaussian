import java.util.Scanner;

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
}