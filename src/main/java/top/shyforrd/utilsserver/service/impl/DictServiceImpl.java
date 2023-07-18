package top.shyforrd.utilsserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.shyforrd.utilsserver.entity.Dict;
import top.shyforrd.utilsserver.service.DictService;
import top.shyforrd.utilsserver.mapper.DictMapper;
import org.springframework.stereotype.Service;
import top.shyforrd.utilsserver.utils.BaseResult;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 14645
 * @description 针对表【dict】的数据库操作Service实现
 * @createDate 2023-05-20 19:11:15
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
        implements DictService {
    @Resource
    private DictMapper dictMapper;

    @Override
    public BaseResult<Map<String, List<Dict>>> getDict(String types) {
        LambdaQueryWrapper<Dict> query = Wrappers.lambdaQuery();
        query.in(Dict::getType, Arrays.asList(types.split(","))).orderByAsc(Dict::getSort);
        return BaseResult.success(dictMapper.selectList(query).stream().collect(Collectors.groupingBy(Dict::getType)));
    }

    @Override
    public BaseResult<String> addRegular(Dict dict) {
        dict.setType("regular");
        dict.setSort(1);
        dictMapper.insert(dict);
        return BaseResult.success();
    }
}




