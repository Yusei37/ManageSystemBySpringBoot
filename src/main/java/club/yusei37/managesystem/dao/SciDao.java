package club.yusei37.managesystem.dao;

/**
 * Created by Yusei on 2018/12/25
 */
import java.util.List;

import club.yusei37.managesystem.bean.Group;
import club.yusei37.managesystem.bean.History;
import club.yusei37.managesystem.bean.Sci;
import club.yusei37.managesystem.bean.SciType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SciDao {

    @Select("SELECT count(*) FROM scilist where typeid=#{typeId}")
    int countTypeNumber(String typeId);

    @Select("SELECT * FROM scitype")
    List<SciType> listAllSciType();

    @Insert("insert into scitype(typeid, typename) values (#{typeId}, #{typeName})")
    void addSciType(SciType sciType);

    @Update("update scitype set typename=#{typeName} where typeid=#{typeId}")
    void modifySciType(SciType sciType);

    @Delete("delete from scitype where typeid=#{typeId}")
    void deleteSciType(String typeId);

    @Select("SELECT * FROM scitype where typeid <> {typeId}")
    SciType readSciType(String typeId);

    @Select("SELECT * FROM scilist")
    List<Sci> listAllSci();

    @Select("SELECT * FROM scilist where userid=#{userId}")
    List<Sci> getSciListByUserId(String userId);

    @Select("SELECT * FROM scilist where userid=#{userId} and status <> '通过'")
    List<Sci> getSciListUncheckedByUserId(String userId);

    @Select("SELECT * FROM scilist where userid=#{userId} and status <> '通过'")
    List<Sci> getSciListCheckedByUserId(String userId);

    @Select("SELECT * FROM scilist where status <> '待审核'")
    List<Sci> getSciListUnchecked();

    @Insert("insert into scilist(sciid, title, userid, begindate, enddate, content, status, typeid) values (#{sciId}," +
            "#{title}," +
            "#{userId}, #{beginDate}, #{endDate}, #{content}, #{status}, #{typeId})")
    void addSci(Sci sci);

    @Update("update scilist set title=#{title}, userid=#{userId}, begindate=#{beginDate}, enddate=#{endDate}, " +
            "content=#{content}, " +
            "status=#{status}, typeid=#{typeId} " +
            "where " +
            "sciid=#{sciId}")
    void modifySci(Sci sci);

    @Update("update scilist set status=#{status} where sciid=#{sciId}")
    void checkSci(String sciId, String status);

    @Insert("insert into history(sciid, result, date) values (#{sciId}, #{result}, #{date})")
    void addHistory(History history);

    @Delete("delete from scilist where sciid=#{sciId}")
    void deleteSci(String sciId);

    @Select("SELECT * FROM scilist where sciid=#{sciId}")
    Sci readSci(String sciId);

    @Select("SELECT * FROM groupmember")
    List<Group> getGroupList();

    @Insert("insert into groupmember(sciid, userid, weight) values (#{sciId}, #{userId}, #{weight})")
    void addGroup(Group group);

    @Update("update groupmember set userid=#{userId} where sciid=#{sciId} and weight=#{weight}")
    void modifyGroup(Group group);

    @Delete("delete from groupmember where sciid=#{sciId} and weight=#{weight}")
    void deleteGroup(String sciId, int weight);

    @Delete("delete from groupmember where sciid=#{sciId}")
    void deleteGroup2(String sciId);

    @Select("SELECT * FROM groupmember where id=#{id}")
    Group readGroup(String id);

    @Select("SELECT userid FROM groupmember where sciid=#{sciId} and weight <> '1'")
    List<String> readGroupBySciId(String sciId);

}

