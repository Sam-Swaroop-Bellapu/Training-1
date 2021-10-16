package model;

public class StudentFee {
    private String rno;
    private float amount;
    private String date;

    public StudentFee(String rno, float amount, String date) {
        this.rno = rno;
        this.amount = amount;
        this.date = date;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
