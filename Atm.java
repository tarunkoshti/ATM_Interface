import java.util.Scanner;

public class Atm {

    Scanner sc = new Scanner(System.in);
   String name;
   String userId;
   String password;
   long accountNo;
   double balance = 50000;
   int transaction = 0;
   String transactionHist="";

   public void register()
   {
       System.out.println("enter your name:");
       name = sc.nextLine();
       System.out.println("enter your userID:");
       userId = sc.nextLine();
       System.out.println("enter your Account number:");
       accountNo = sc.nextLong();
       System.out.println("create a strong password");
       sc.nextLine();
       password = sc.nextLine();
       System.out.println("You are registered successfully.");
       System.out.println("\n---------------------------------------------------------------------");
   }

    public void login()
    {
        boolean b = false;
        int attempt =0;
        String pwd;
        String uID;
        while(!b)
        {
//            System.out.println("\n---------------------------------------------------------------------");
            if (attempt ==0)
            System.out.println("\nplease enter your userID");
            uID = sc.nextLine();
            if (uID.equals(userId))
            {
                while (!b)
                {
                    if (attempt ==0)
                        System.out.println("please enter your password");
                        pwd = sc.nextLine();
                    if (pwd.equals(password)) {
                        System.out.println("Login Successfully");
                        b = true;
                    } else {
                        if (attempt >= 2){
                            System.out.println("you entered wrong password again\nMaximum limit exceeded");
                            System.out.println("Try after 24 hours");
                            System.exit(0);
                        }
                        System.out.println("Incorrect password");
                        System.out.println("Reenter password");
                        attempt++;
                    }
                }
            }
            else
            {
                if (attempt >= 2) {
                    System.out.println("you enter wrong password again \nMaximum limit exceeded");
                    System.out.println("Try after 24 hours");
                    System.exit(0);
                }
                System.out.println("UserID is not found ");
                System.out.println("Reenter UserID");
                attempt++;
            }
        }
    }

    public void withdraw(){
        System.out.println("Enter amount you want to withdraw");
        long amount =sc.nextLong();
        try {
            if (balance >= amount) {
                transaction++;
                balance = balance - amount;
                System.out.println("withdraw successfully \n");
                String s = amount + " rs Successfully withdraw";
                transactionHist = "\n"+transactionHist.concat(s);
            } else System.out.println("Insufficient balance");
        } catch (NullPointerException e) {

        }
    }

    public void deposit(){
        System.out.println("Enter amount you want to deposit");
        long amount = sc.nextLong();
        try {
            if (amount <= 50000) {
                transaction++;
                balance = balance + amount;
                System.out.println("successfully deposit");
                String s = amount + " rs deposit successfully ";
                transactionHist = "\n"+transactionHist.concat(s);
            } else {
                System.out.println("sorry deposit amount limit is 50000.00");
            }
        } catch (NullPointerException e) {

        }
    }

    public void transfer(){
       Scanner sc1= new Scanner(System.in);
        System.out.println("Enter recipient name");
        String recipient = sc1.nextLine();
        System.out.println("Enter the recipient account number");
        double transfer_ac= sc.nextDouble();
        System.out.println("Enter transfer amount ");
        long amount = sc.nextLong();
        try {
            if (balance >= amount) {
                if (amount <= 50000) {
                    transaction++;
                    balance = balance - amount;
                    System.out.println("transaction successfully to " + recipient);
                    String s = amount + " rs transfer to " + recipient;
                    transactionHist = "\n"+transactionHist.concat(s);
                } else {
                    System.out.println("sorry transfer limit is 50000");
                }
            } else {
                System.out.println("Insufficient balance");
            }
        } catch (NullPointerException e) {
        }
    }

    public void checkBalance(){
        System.out.println(balance+" rs");
    }

    public void transactionHist(){
        if (transaction == 0) {
            System.out.println("Empty");
        } else {
            System.out.println("\n"+transactionHist);
        }
    }
    public int takeInput(int limit) {
       int input =0;
       boolean flag = false;
        while (!flag) {
            try {
                Scanner sc1 = new Scanner(System.in);
                input = sc1.nextInt();
                flag = true;
                if (input > limit || input < 1) {
                    System.out.println("choose number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only Integer value");
                flag = false;
            }
        }
        return input;
    }
    public static void main(String[] args) {
//       Scanner sc1 = new Scanner(System.in);
        // create ATM object.
        Atm a = new Atm();
        System.out.println("\n...................... WELCOME TO OASIS BANK ......................");
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose an option.");
        int option = a.takeInput(2);

        if (option == 1) {
            a.register();
            while (true) {
                System.out.println("\n1.login \n2.Exit");
                System.out.println("Enter your choice");
                int o = a.takeInput(2);
                if (o == 1) {
                    a.login();
                    System.out.println("\n...................... WELCOME " + a.name + " ......................");
                    boolean cancel = false;
                    while (!cancel) {
                        System.out.println("\n1.Withdraw");
                        System.out.println("2.Deposit");
                        System.out.println("3.Transfer");
                        System.out.println("4.Check Balance");
                        System.out.println("5.Transaction history");
                        System.out.println("6.cancel");
                        System.out.println("\nEnter your choice");
                        int c = a.takeInput(6);
                        switch (c) {
                            case 1 -> a.withdraw();
                            case 2 -> a.deposit();
                            case 3 -> a.transfer();
                            case 4 -> a.checkBalance();
                            case 5 -> a.transactionHist();
                            case 6 -> cancel = true;
                        }
                    }
                } else {
                    System.out.println("............ THANK YOU FOR USING OASIS BANK");
                    System.exit(0);
                }
            }
        }
   }
}
