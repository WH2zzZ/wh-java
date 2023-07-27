package com.oowanghan.spring.scop;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenheng
 * @date 2018/5/7
 */
public class MobileRequestScopeFactory implements Scope {

    private final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        final Map<String, Object> scopeMap = threadLocal.get();

        Object scopedObject = scopeMap.get(name);

        if (scopedObject == null) {
            scopedObject = objectFactory.getObject();
            scopeMap.put(name, scopedObject);
        }

        return scopedObject;

    }

    @Override
    public Object remove(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        return;
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
