package org.daistudy.springmvc.test.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {
        try{
            return simpleDateFormat.parse(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
