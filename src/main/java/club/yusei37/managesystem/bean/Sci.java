package club.yusei37.managesystem.bean;

/**
 * Created by Yusei on 2018/12/25
 */
import java.util.Date;
import java.util.List;

public class Sci {
    private String sciId;
    private String title;
    private String userId;
    private Date beginDate;
    private Date endDate;
    private String content;
    private String status;
    private String typeId;
    private List<String> groupmember;

    public Sci() {
    }

    public Sci(String title, String userId, Date beginDate, Date endDate, String content, String status, String
            typeId, List<String> gp) {
        this.title = title;
        this.userId = userId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.content = content;
        this.status = status;
        this.typeId = typeId;
        this.groupmember = gp;
    }

    public String getSciId() {
        return sciId;
    }
    public void setSciId(String sciId) {
        this.sciId = sciId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public List<String> getGroupmember() {
        return groupmember;
    }
    public void setGroupmember(List<String> groupmember) {
        this.groupmember = groupmember;
    }
    @Override
    public String toString() {
        return "Sci [sciId=" + sciId + ", title=" + title + ", userId=" + userId + ", beginDate=" + beginDate
                + ", endDate=" + endDate + ", content=" + content + ", status=" + status + ", typeId=" + typeId
                + ", groupmember=" + groupmember + "]";
    }

}