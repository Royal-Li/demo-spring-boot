package com.lzscoding.demo.spring.abstractt;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 测试自动注入抽象类实现类
 *
 * @author 180626
 */
@Service
@Slf4j
public class TestInjection {

    private Map<String, AbstractParent> abstractParentMap = new HashMap<>(4);

    public TestInjection(List<AbstractParent> abstractParent) {
        if (!CollectionUtils.isEmpty(abstractParent)) {
            abstractParentMap = abstractParent.stream().collect(
                    Collectors.toMap(AbstractParent::name, Function.identity()));
            log.info("after build map ->{}", JSONObject.toJSONString(abstractParentMap));
        }
    }
}
