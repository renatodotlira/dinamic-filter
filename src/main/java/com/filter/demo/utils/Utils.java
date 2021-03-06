package com.filter.demo.utils;

import com.filter.demo.exceptions.DefaultException;
import com.filter.demo.repository.GenericRepository;

import java.time.LocalDate;
import java.util.Optional;

public class Utils {

    public static void checkNull(Object var, String nameAtribute){
        if(var == null || var.equals(""))
            throw new DefaultException(nameAtribute+".is.mandatory");
    }

    public static void checkNullOrZero(Integer var, String nameAtribute){
        checkNull(var, nameAtribute);
        checkNullOrZero(new Double(var), nameAtribute);
    }

    public static void checkNullOrZero(Long var, String nameAtribute){
        checkNull(var, nameAtribute);
        checkNullOrZero(new Double(var), nameAtribute);
    }

    public static void checkNullOrZero(Double var, String nameAtribute){
        checkNull(var, nameAtribute);
        if(var == 0)
            throw new DefaultException(nameAtribute+".is.mandatory");
    }

    public static void checkNullOrZero(Float var, String nameAtribute){
        checkNull(var, nameAtribute);
        checkNullOrZero(new Double(var), nameAtribute);
    }

    public static void checkIfNameExist(String name, GenericRepository repository){
        Optional<Object> objectOptional = repository.findByName(name);
        if(objectOptional.isPresent())
            throw new DefaultException("name.registered");
    }

    public static String getYear(){
        LocalDate localDate = LocalDate.now();
        String year = localDate.getYear()+"";
        return year.substring(2);
    }

    public static String removeLikeCharacter(String value){
        String ret = "";
        if(value.length()==0)
            return "";
        if(value.charAt(0) == '%')
            ret = value.substring(1, value.length());
        else
            ret = value;
        if(ret.length()==0)
            return "";
        if(ret.charAt(ret.length()-1) == '%'){
            ret = ret.substring(0, ret.length()-1);
        }
        return ret;
    }
}
