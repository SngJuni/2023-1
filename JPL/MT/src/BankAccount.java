public class BankAccount {  // class for bank account
    private String bankNumber;
    private int pinCode;
    private double balance;
    private User bankUser;

    public BankAccount(String bankNumber, int pinCode, double balance, User bankUser) {
        setBankNumber(bankNumber);
        setPinCode(pinCode);
        setBalance(balance);
        setBankUser(bankUser);
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getBankUser() {
        return bankUser;
    }

    public void setBankUser(User bankUser) {
        this.bankUser = bankUser;
    }
}
