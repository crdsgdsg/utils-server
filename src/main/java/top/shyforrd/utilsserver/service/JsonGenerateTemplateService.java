package top.shyforrd.utilsserver.service;

import top.shyforrd.utilsserver.entity.JsonGenerateTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import top.shyforrd.utilsserver.entity.dto.JsonGenerateTemplateDto;
import top.shyforrd.utilsserver.utils.BaseResult;

/**
* @author 14645
* @description 针对表【json_generate_template】的数据库操作Service
* @createDate 2023-05-22 14:13:04
*/
public interface JsonGenerateTemplateService extends IService<JsonGenerateTemplate> {

    BaseResult<String> addJsonTemplate(JsonGenerateTemplateDto jsonGenerateTemplateDto);

    BaseResult<JsonGenerateTemplateDto> findJsonTemplateById(String id);
}
