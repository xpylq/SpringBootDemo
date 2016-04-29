package yzh.spring.boot;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzyouzhihao on 2016/4/27.
 */
public class CustomListHttpMessageConverter implements HttpMessageConverter<List> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        if ((List.class).isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return new ArrayList<MediaType>();
    }

    @Override
    public List read(Class<? extends List> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        System.out.println(clazz);
        return null;
    }

    @Override
    public void write(List list, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        System.out.println("write");

    }
}
