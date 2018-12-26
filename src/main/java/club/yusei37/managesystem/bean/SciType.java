package club.yusei37.managesystem.bean;

/**
 * Created by Yusei on 2018/12/25
 */
public class SciType {
    private String typeId;
    private String typeName;

    public SciType() {
    }

    public SciType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @Override
    public String toString() {
        return "SciType [typeId=" + typeId + ", typeName=" + typeName + "]";
    }
}

