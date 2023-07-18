package top.shyforrd.utilsserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.shyforrd.utilsserver.entity.Dict;
import top.shyforrd.utilsserver.entity.JsonGenerateTemplate;
import top.shyforrd.utilsserver.entity.dto.JsonGenerateTemplateDto;
import top.shyforrd.utilsserver.mapper.DictMapper;
import top.shyforrd.utilsserver.service.DictService;
import top.shyforrd.utilsserver.service.JsonGenerateTemplateService;
import top.shyforrd.utilsserver.utils.BaseResult;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jsonProduce")
public class JsonProduceController {
    @Resource
    private DictService dictService;
    @Resource
    private JsonGenerateTemplateService jsonGenerateTemplateService;

    @PostMapping("addRegular")
    public BaseResult<String> getDict(@Validated @RequestBody Dict dict) {
        return dictService.addRegular(dict);
    }

    @PostMapping("addJsonTemplate")
    public BaseResult<String> addJsonTemplate(@Validated @RequestBody JsonGenerateTemplateDto jsonGenerateTemplateDto) {
        return jsonGenerateTemplateService.addJsonTemplate(jsonGenerateTemplateDto);
    }
    @PostMapping("findJsonTemplateById")
    public BaseResult<JsonGenerateTemplateDto> findJsonTemplateById(@Validated @NotBlank(message = "ID不能为空") String id) {

        return jsonGenerateTemplateService.findJsonTemplateById(id);
    }

    @PostMapping("getAllJsonTemplate")
    public BaseResult<List<JsonGenerateTemplate>> getAllJsonTemplate() {
        return BaseResult.success(jsonGenerateTemplateService.list(Wrappers.lambdaQuery(JsonGenerateTemplate.class).eq(JsonGenerateTemplate::getParentId, 0)));
    }
}
