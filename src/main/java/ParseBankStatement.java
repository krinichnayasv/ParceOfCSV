import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParseBankStatement {

    private String numberAccount;
    private String typeOperation;
    private Date dateOfOperation;
    private Double plus;
    private Double minus;

    List<ParseBankStatement> data = new ArrayList<>();



    public ParseBankStatement(String numberAccount, Date dateOfOperation, String typeOperation,
                              Double plus, Double minus)
    {
        this.numberAccount = numberAccount;
        this.dateOfOperation = dateOfOperation;
        this.typeOperation = typeOperation;
        this.plus = plus;
        this.minus = minus;
    }


    public void addParseData(ParseBankStatement parse){
        data.add(parse);
    }


    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public Double getPlus() {
        return plus;
    }

    public void setPlus(Double plus) {
        this.plus = plus;
    }

    public Double getMinus() {
        return minus;
    }

    public void setMinus(Double minus) {
        this.minus = minus;
    }


}
