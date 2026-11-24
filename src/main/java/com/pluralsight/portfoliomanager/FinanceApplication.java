package com.pluralsight.portfoliomanager;

import com.pluralsight.portfoliomanager.model.Account;
import com.pluralsight.portfoliomanager.model.BankAccount;
import com.pluralsight.portfoliomanager.model.CreditCard;
import com.pluralsight.portfoliomanager.model.FixedAsset;
import com.pluralsight.portfoliomanager.model.Gold;
import com.pluralsight.portfoliomanager.model.House;
import com.pluralsight.portfoliomanager.model.Jewelry;
import com.pluralsight.portfoliomanager.model.Portfolio;
import com.pluralsight.portfoliomanager.model.Valuable;

import java.math.BigDecimal;
import java.util.Scanner;

public class FinanceApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio("Family Portfolio", "Pam");

        portfolio.add(new House("Ranch house", new BigDecimal("325000.00"), 1998, 1850, 3));
        portfolio.add(new Gold("Gold coins", new BigDecimal("12000.00"), 5.0));
        portfolio.add(new Jewelry("Grandma's ring", new BigDecimal("2500.00"), 14.0));
        portfolio.add(new BankAccount(123, "Checking", new BigDecimal("12500.00")));
        portfolio.add(new CreditCard(789, "Visa", new BigDecimal("4500.00")));

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1) Add a house");
            System.out.println("2) Add gold");
            System.out.println("3) Add jewelry");
            System.out.println("4) Add a bank account");
            System.out.println("5) Add a credit card");
            System.out.println("6) Show portfolio");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addHouse(scanner, portfolio);
                    break;
                case "2":
                    addGold(scanner, portfolio);
                    break;
                case "3":
                    addJewelry(scanner, portfolio);
                    break;
                case "4":
                    addBankAccount(scanner, portfolio);
                    break;
                case "5":
                    addCreditCard(scanner, portfolio);
                    break;
                case "6":
                    displayPortfolio(portfolio);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Please choose an option from the menu.");
                    break;
            }
        }

        System.out.println("Goodbye!");
    }

    private static void addHouse(Scanner scanner, Portfolio portfolio) {
        String name = promptForText(scanner, "Name: ");
        BigDecimal marketValue = promptForMoney(scanner, "Market value: ");
        int yearBuilt = promptForInt(scanner, "Year built: ");
        int squareFeet = promptForInt(scanner, "Square feet: ");
        int bedrooms = promptForInt(scanner, "Bedrooms: ");

        portfolio.add(new House(name, marketValue, yearBuilt, squareFeet, bedrooms));
        System.out.println(name + " was added.");
    }

    private static void addGold(Scanner scanner, Portfolio portfolio) {
        String name = promptForText(scanner, "Name: ");
        BigDecimal marketValue = promptForMoney(scanner, "Market value: ");
        double weight = promptForDouble(scanner, "Weight: ");

        portfolio.add(new Gold(name, marketValue, weight));
        System.out.println(name + " was added.");
    }

    private static void addJewelry(Scanner scanner, Portfolio portfolio) {
        String name = promptForText(scanner, "Name: ");
        BigDecimal marketValue = promptForMoney(scanner, "Market value: ");
        double karat = promptForDouble(scanner, "Karat: ");

        portfolio.add(new Jewelry(name, marketValue, karat));
        System.out.println(name + " was added.");
    }

    private static void addBankAccount(Scanner scanner, Portfolio portfolio) {
        int accountNumber = promptForInt(scanner, "Account number: ");
        String name = promptForText(scanner, "Account name: ");
        BigDecimal balance = promptForMoney(scanner, "Balance: ");

        portfolio.add(new BankAccount(accountNumber, name, balance));
        System.out.println(name + " was added.");
    }

    private static void addCreditCard(Scanner scanner, Portfolio portfolio) {
        int accountNumber = promptForInt(scanner, "Account number: ");
        String name = promptForText(scanner, "Card name: ");
        BigDecimal balance = promptForMoney(scanner, "Balance owed: ");

        portfolio.add(new CreditCard(accountNumber, name, balance));
        System.out.println(name + " was added.");
    }

    private static void displayPortfolio(Portfolio portfolio) {
        System.out.println();
        System.out.println(portfolio.getName() + " (owner: " + portfolio.getOwner() + ")");

        for (Valuable asset : portfolio.getAssets()) {
            System.out.println(describe(asset));
        }

        System.out.println("Net value: " + formatMoney(portfolio.getValue()));

        Valuable mostValuable = portfolio.getMostValuable();
        Valuable leastValuable = portfolio.getLeastValuable();

        if (mostValuable != null) {
            System.out.println("Most valuable: " + describe(mostValuable));
            System.out.println("Least valuable: " + describe(leastValuable));
        }
    }

    private static String describe(Valuable asset) {
        String name = "Unknown";

        if (asset instanceof FixedAsset fixedAsset) {
            name = fixedAsset.getName();
        } else if (asset instanceof Account account) {
            name = account.getName();
        } else if (asset instanceof Portfolio otherPortfolio) {
            name = otherPortfolio.getName();
        }

        return name + ": " + formatMoney(asset.getValue());
    }

    private static String formatMoney(BigDecimal amount) {
        return String.format("$%.2f", amount);
    }

    private static int promptForInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double promptForDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static String promptForText(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static BigDecimal promptForMoney(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an amount like 1500.00");
            }
        }
    }
}