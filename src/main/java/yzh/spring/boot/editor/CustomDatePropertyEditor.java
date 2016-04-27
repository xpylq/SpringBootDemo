package yzh.spring.boot.editor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzyouzhihao on 2016/4/26.
 * 自定义一个请求参数中date类型自动转化器
 */
public class CustomDatePropertyEditor extends PropertyEditorSupport {
    public static String DATE_FORMAT_STR = "yyyy-MM-dd";

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STR);
            Date date = format.parse(text);
            setValue(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

    }
}
