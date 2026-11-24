# Portfolio Manager

A Java console app from Workbook 6a. It uses a `Valuable` interface so that very different things, like a house, gold, a bank account and a credit card, can all report what they are worth.

## Class Diagram

![ProjectDiagram.png](ProjectDiagram.png)![img.png](ProjectDiagram.png)

## How It Works

- `Valuable` is an interface with one method, `getValue()`.
- `FixedAsset` is an abstract class for things you own: `House`, `Gold` and `Jewelry`.
- `Account` is an abstract class for money accounts: `BankAccount` and `CreditCard`.
- Both `FixedAsset` and `Account` say `implements Valuable`, so every class below them has `getValue()`.
- A `CreditCard` returns its balance as a negative number, because that is money owed.
- `Portfolio` keeps a `List<Valuable>` and finds the net value, the most valuable asset and the least valuable asset.

## Project Structure

```text
portfolio-manager
├── pom.xml
├── README.md
├── Diagram.png
└── src/main/java/com/pluralsight/portfoliomanager
    ├── FinanceApplication.java
    └── model
        ├── Valuable.java
        ├── Portfolio.java
        ├── FixedAsset.java
        ├── House.java
        ├── Gold.java
        ├── Jewelry.java
        ├── Account.java
        ├── BankAccount.java
        └── CreditCard.java
```

## Sample Output

Choosing option 6 with the five starting assets:

```text
Family Portfolio (owner: Pam)
Ranch house: $325000.00
Gold coins: $12000.00
Grandma's ring: $2500.00
Checking: $12500.00
Visa: $-4500.00
Net value: $347500.00
Most valuable: Ranch house: $325000.00
Least valuable: Visa: $-4500.00
```

Checked by hand: 325,000 + 12,000 + 2,500 + 12,500 − 4,500 = 347,500.

## Why Portfolio Implements Valuable

A portfolio has a value too: the net value of everything in it. So `Portfolio` says `implements Valuable` and puts `@Override` on `getValue()`.

Because a `Portfolio` is now a `Valuable`, a portfolio can hold another portfolio. 
For example, a family portfolio can hold each child's savings portfolio. When the family portfolio adds up its assets, it calls `getValue()` on the child's portfolio, which adds up its own assets first.

## What I Learned

`account1` is declared as a `BankAccount`, so I can call `deposit` on it. `account2` is declared as a `Valuable`, so the only method I can call is `getValue()`, even though the object is really a `BankAccount`. 
The variable's type decides what I can call, and the object decides which version runs.

That is why `Portfolio` can keep houses, gold, accounts and even other portfolios in one `List<Valuable>`: it only ever needs to call `getValue()` on them.
