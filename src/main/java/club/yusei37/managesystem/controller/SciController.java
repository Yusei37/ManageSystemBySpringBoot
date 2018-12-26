package club.yusei37.managesystem.controller;

import club.yusei37.managesystem.service.SciService;
import club.yusei37.managesystem.bean.Sci;
import club.yusei37.managesystem.bean.SciType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yusei on 2018/12/25
 */
@CrossOrigin
@RestController
public class SciController {

    @Autowired
    private SciService sciService;

    @RequestMapping(value="/sci",method=RequestMethod.GET)
    public List<Sci> getSciList(){
        return sciService.listAllSci();
    }

    @RequestMapping(value="/sci/unchecked",method=RequestMethod.GET)
    public List<Sci> getSciListUnchecked(){
        return sciService.getSciListUnchecked();
    }

    @RequestMapping(value="/sci/unchecked/{userid}",method=RequestMethod.GET)
    public List<Sci> getSciListUncheckedByUserId(@PathVariable("userid") String userId){
        return sciService.getSciListUncheckedByUserId(userId);
    }

    @RequestMapping(value="/sci/checked/{userid}",method=RequestMethod.GET)
    public List<Sci> getSciListCheckedByUserId(@PathVariable("userid") String userId){
        return sciService.getSciListCheckedByUserId(userId);
    }

    @RequestMapping(value="/sci",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    public String addSci(@RequestBody Sci sci) {
        System.out.println(sci);
        sciService.addSci(sci);
        return "ok";
    }

    @RequestMapping(value="/sci/{sciid}",method=RequestMethod.DELETE)
    public String deleteSci(@PathVariable("sciid") String sciId){
        sciService.deleteSci(sciId);
        return "ok";
    }

    @RequestMapping(value="/sci/{sciid}",method=RequestMethod.PUT,produces={"application/json;charset=UTF-8"})
    public String modifySci(@RequestBody Sci sci){
        System.out.println(sci);
        sciService.modifySci(sci);
        return "ok";
    }

    @RequestMapping(value="/sci/{sciid}/status",method=RequestMethod.PATCH,produces={"application/json;charset=UTF-8"})
    public String check(@PathVariable("sciid") String sciId, @RequestBody String status){
        System.out.println(sciId);
        sciService.checkSci(sciId, status);
        return "ok";
    }

    @RequestMapping(value="/sciType",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    public String addSciType(@RequestBody SciType sciType) {
        System.out.println(sciType);
        sciService.addSciType(sciType);
        return "ok";
    }

    @RequestMapping(value="/sciType/{typeid}",method=RequestMethod.DELETE)
    public String deleteSciType(@PathVariable("typeid") String typeId){
        return sciService.deleteSciType(typeId);
    }

    @RequestMapping(value="/sciType/{typeid}",method=RequestMethod.PUT,produces={"application/json;charset=UTF-8"})
    public String modifySciType(@RequestBody SciType sciType){
        sciService.modifySciType(sciType);
        return "ok";
    }

    @RequestMapping(value="/sciType",method=RequestMethod.GET)
    public List<SciType> getSciTypeList(){
        return sciService.listAllSciType();
    }
}
