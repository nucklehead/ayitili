package com.colorofhope.ayitili.rest.RestAdvisors;

import com.colorofhope.ayitili.model.DBModel;
import com.colorofhope.ayitili.rest.controllers.DBModelESController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice(assignableTypes={DBModelESController.class})
public class SearchResultAdvice implements ResponseBodyAdvice<List<DBModel>> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public List<DBModel> beforeBodyWrite(List<DBModel> models, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return removeSearchResponseIgnoreFields(models);
    }

    private List<DBModel> removeSearchResponseIgnoreFields(List<DBModel> models) {
        models.forEach(this::removeSearchResponseIgnoreFields);
        return models;
    }

    private void removeSearchResponseIgnoreFields(DBModel model) {
        model.clearSearchResponseIgnoreFields();
    }
}
