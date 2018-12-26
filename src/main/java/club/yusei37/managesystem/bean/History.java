package club.yusei37.managesystem.bean;

/**
 * Created by Yusei on 2018/12/25
 */
import java.util.Date;

public class History {

    private int id;
    private String sciId;
    private String result;
    private Date date;

    public History() {
    }

    public History(String sciId, String result, Date date) {
        this.sciId = sciId;
        this.result = result;
        this.date = date;
    }

    public History(int id, String sciId, String result, Date date) {
        this.id = id;
        this.sciId = sciId;
        this.result = result;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSciId() {
        return sciId;
    }
    public void setSciId(String sciId) {
        this.sciId = sciId;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "History [id=" + id + ", sciId=" + sciId + ", result=" + result + ", date=" + date + "]";
    }

}

