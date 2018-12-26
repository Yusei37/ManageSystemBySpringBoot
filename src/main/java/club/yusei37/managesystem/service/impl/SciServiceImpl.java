package club.yusei37.managesystem.service.impl;

import club.yusei37.managesystem.bean.Group;
import club.yusei37.managesystem.bean.History;
import club.yusei37.managesystem.bean.Sci;
import club.yusei37.managesystem.bean.SciType;
import club.yusei37.managesystem.dao.SciDao;
import club.yusei37.managesystem.service.SciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yusei on 2018/12/25
 */
@Service
public class SciServiceImpl implements SciService {

    @Autowired
    private SciDao sciDao;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<SciType> listAllSciType() {
        return sciDao.listAllSciType();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addSciType(SciType sciType) {
        sciDao.addSciType(sciType);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void modifySciType(SciType sciType) {
        sciDao.modifySciType(sciType);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public String deleteSciType(String typeId) {
        int num = sciDao.countTypeNumber(typeId);
        if (num == 0) {
            sciDao.deleteSciType(typeId);
        }
        else {
            return "fail";
        }
        return "ok";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public SciType readSciType(String typeId) {
        return sciDao.readSciType(typeId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Sci> listAllSci() {
        return appendGroup(sciDao.listAllSci());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Sci> getSciListByUserId(String userId) {
        return appendGroup(sciDao.getSciListByUserId(userId));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Sci> getSciListUncheckedByUserId(String userId) {
        return appendGroup(sciDao.getSciListUncheckedByUserId(userId));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Sci> getSciListCheckedByUserId(String userId) {
        return appendGroup(sciDao.getSciListCheckedByUserId(userId));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Sci> getSciListUnchecked() {
        return appendGroup(sciDao.getSciListUnchecked());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addSci(Sci sci) {
        Date d = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        sci.setSciId(format.format(d));
        sciDao.addSci(sci);

        Group group = new Group();
        group.setWeight(1);
        group.setSciId(sci.getSciId());
        group.setUserId(sci.getUserId());
        addGroup(group);

        List<String> groupmember = sci.getGroupmember();
        for (int i=0; i<groupmember.size(); i++) {
            Group gp = new Group();
            gp.setWeight(i + 2);
            gp.setSciId(sci.getSciId());
            gp.setUserId(groupmember.get(i));
            addGroup(gp);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void modifySci(Sci sci) {
        sciDao.modifySci(sci);

        Group group = new Group();
        group.setWeight(1);
        group.setSciId(sci.getSciId());
        group.setUserId(sci.getUserId());
        modifyGroup(group);

        List<String> groupmember = sci.getGroupmember();
        if (groupmember.size() == 0) {
            deleteGroup(sci.getSciId(), 2);
            deleteGroup(sci.getSciId(), 3);
        }
        else if (groupmember.size() == 1) {
            deleteGroup(sci.getSciId(), 3);
        }

        for (int i=0; i<groupmember.size(); i++) {
            Group gp = new Group();
            gp.setWeight(i + 2);
            gp.setSciId(sci.getSciId());
            gp.setUserId(groupmember.get(i));
            modifyGroup(gp);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void checkSci(String sciId, String status) {
        sciDao.checkSci(sciId, status);
        addHistory(sciId, status);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addHistory(String sciId, String result) {
        History history = new History();
        history.setDate(new Date());
        history.setSciId(sciId);
        history.setResult(result);
        sciDao.addHistory(history);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void deleteSci(String sciId) {
        sciDao.deleteSci(sciId);
        sciDao.deleteGroup2(sciId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Sci readSci(String sciId) {
        return sciDao.readSci(sciId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Group> getGroupList() {
        return sciDao.getGroupList();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void addGroup(Group group) {
        sciDao.addGroup(group);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void modifyGroup(Group group) {
        sciDao.modifyGroup(group);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void deleteGroup(String sciId, int weight) {
        sciDao.deleteGroup(sciId, weight);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void deleteGroup2(String sciId) {
        sciDao.deleteGroup2(sciId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Group readGroup(String id) {
        return sciDao.readGroup(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<Sci> appendGroup(List<Sci> scis) {
        for(Sci sci: scis) {
            List<String> lg = sciDao.readGroupBySciId(sci.getSciId());
            sci.setGroupmember(lg);
        }
        return scis;
    }
}
