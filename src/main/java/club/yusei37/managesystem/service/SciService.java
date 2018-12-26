package club.yusei37.managesystem.service;

import club.yusei37.managesystem.bean.Group;
import club.yusei37.managesystem.bean.Sci;
import club.yusei37.managesystem.bean.SciType;

import java.util.List;

/**
 * Created by Yusei on 2018/12/25
 */
public interface SciService {

    List<SciType> listAllSciType();

    void addSciType(SciType sciType);

    void modifySciType(SciType sciType);

    String deleteSciType(String typeId);

    SciType readSciType(String typeId);

    List<Sci> listAllSci();

    List<Sci> getSciListByUserId(String userId);

    List<Sci> getSciListUncheckedByUserId(String userId);

    List<Sci> getSciListCheckedByUserId(String userId);

    List<Sci> getSciListUnchecked();

    void addSci(Sci sci);

    void modifySci(Sci sci);

    void checkSci(String sciId, String status);

    void addHistory(String sciId, String result);

    void deleteSci(String sciId);

    Sci readSci(String sciId);

    List<Group> getGroupList();

    void addGroup(Group group);

    void modifyGroup(Group group);

    void deleteGroup(String sciId, int weight);

    void deleteGroup2(String sciId);

    Group readGroup(String id);

    List<Sci> appendGroup(List<Sci> scis);
}
