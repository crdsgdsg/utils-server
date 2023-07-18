package top.shyforrd.utilsserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import top.shyforrd.utilsserver.entity.JsonGenerateTemplate;
import top.shyforrd.utilsserver.entity.dto.JsonGenerateTemplateDto;
import top.shyforrd.utilsserver.service.JsonGenerateTemplateService;
import top.shyforrd.utilsserver.mapper.JsonGenerateTemplateMapper;
import org.springframework.stereotype.Service;
import top.shyforrd.utilsserver.utils.BaseResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 14645
 * @description 针对表【json_generate_template】的数据库操作Service实现
 * @createDate 2023-05-22 14:13:04
 */
@Service
public class JsonGenerateTemplateServiceImpl extends ServiceImpl<JsonGenerateTemplateMapper, JsonGenerateTemplate>
        implements JsonGenerateTemplateService {
    @Resource
    private JsonGenerateTemplateMapper jsonGenerateTemplateMapper;

    @Override
    @Transactional
    public BaseResult<String> addJsonTemplate(JsonGenerateTemplateDto jsonGenerateTemplateDto) {
        JsonGenerateTemplate jsonGenerateTemplate = new JsonGenerateTemplate();
        BeanUtils.copyProperties(jsonGenerateTemplateDto, jsonGenerateTemplate);
        jsonGenerateTemplate.setParentId(0);
        this.save(jsonGenerateTemplate);
        List<JsonGenerateTemplateDto> child = jsonGenerateTemplateDto.getChild();
        if (CollectionUtils.isNotEmpty(child)) {
            child.forEach(item -> {
                item.setParentId(jsonGenerateTemplate.getId());
                item.setNumber(jsonGenerateTemplate.getId());
            });
            deepInsert(child);
        }
        return BaseResult.success();
    }

    @Override
    public BaseResult<JsonGenerateTemplateDto> findJsonTemplateById(String id) {
        JsonGenerateTemplate jsonGenerateTemplate = this.getById(id);
        JsonGenerateTemplateDto jsonGenerateTemplateDto = new JsonGenerateTemplateDto();
        BeanUtils.copyProperties(jsonGenerateTemplate, jsonGenerateTemplateDto);
        List<JsonGenerateTemplate> list = this.list(Wrappers.lambdaQuery(JsonGenerateTemplate.class).eq(JsonGenerateTemplate::getNumber, id));
        generateTree(jsonGenerateTemplateDto, list);
        return BaseResult.success(jsonGenerateTemplateDto);
    }

    private void generateTree(JsonGenerateTemplateDto jsonGenerateTemplateDto, List<JsonGenerateTemplate> list) {
        List<JsonGenerateTemplateDto> collect = list.stream().filter(jsonGenerateTemplate -> jsonGenerateTemplate.getParentId().equals(jsonGenerateTemplateDto.getId())).map(jsonGenerateTemplate -> {
            JsonGenerateTemplateDto map = new JsonGenerateTemplateDto();
            BeanUtils.copyProperties(jsonGenerateTemplate, map);
            return map;
        }).collect(Collectors.toList());
        jsonGenerateTemplateDto.setChild(collect);
        jsonGenerateTemplateDto.setId(null);
        if (CollectionUtils.isNotEmpty(collect)) {
            collect.forEach(item -> generateTree(item, list));
        }
    }

    private void deepInsert(List<JsonGenerateTemplateDto> jsonGenerateTemplateList) {
        List<JsonGenerateTemplate> collect = jsonGenerateTemplateList.stream().map(e -> {
            JsonGenerateTemplate jsonGenerateTemplate = new JsonGenerateTemplate();
            BeanUtils.copyProperties(e, jsonGenerateTemplate);
            return jsonGenerateTemplate;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
        for (int i = 0; i < jsonGenerateTemplateList.size(); i++) {
            JsonGenerateTemplateDto jsonGenerateTemplateDto = jsonGenerateTemplateList.get(i);
            JsonGenerateTemplate jsonGenerateTemplate = collect.get(i);
            List<JsonGenerateTemplateDto> child = jsonGenerateTemplateDto.getChild();
            if (CollectionUtils.isNotEmpty(child)) {
                child.forEach(e -> {
                    e.setNumber(jsonGenerateTemplate.getNumber());
                    e.setParentId(jsonGenerateTemplate.getId());
                });
                deepInsert(child);
            }
        }
    }
}




