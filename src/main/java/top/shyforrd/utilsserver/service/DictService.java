package top.shyforrd.utilsserver.service;

import top.shyforrd.utilsserver.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import top.shyforrd.utilsserver.entity.dto.JsonGenerateTemplateDto;
import top.shyforrd.utilsserver.utils.BaseResult;

import java.util.List;
import java.util.Map;

/**
* @author 14645
* @description 针对表【dict】的数据库操作Service
* @createDate 2023-05-20 19:11:15
*/
public interface DictService extends IService<Dict> {

    BaseResult<Map<String, List<Dict>>> getDict(String types);

    BaseResult<String> addRegular(Dict dict);

}
