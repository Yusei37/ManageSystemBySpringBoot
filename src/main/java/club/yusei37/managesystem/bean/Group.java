package club.yusei37.managesystem.bean;

/**
 * Created by Yusei on 2018/12/25
 */
public class Group {

    private int id;
    private String sciId;
    private String userId;
    private int weight;

    public Group() {
    }

    public Group(String sciId, String userId, int weight) {
        this.sciId = sciId;
        this.userId = userId;
        this.weight = weight;
    }

    public Group(int id, String sciId, String userId, int weight) {
        this.id = id;
        this.sciId = sciId;
        this.userId = userId;
        this.weight = weight;
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
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "Group [id=" + id + ", sciId=" + sciId + ", userId=" + userId + ", weight=" + weight + "]";
    }
}

