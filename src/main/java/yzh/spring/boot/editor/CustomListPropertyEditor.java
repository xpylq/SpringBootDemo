package main.java.yzh.spring.boot.editor;

import java.beans.PropertyEditorSupport;

/**
 * Created by hzyouzhihao on 2016/4/26.
 */
public class CustomListPropertyEditor extends PropertyEditorSupport {
    @Override
    public Object getValue() {
        Object orginObject = super.getValue();
        return new Object();
    }
}
