package top.shyforrd.utilsserver.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.shyforrd.utilsserver.entity.Dict;
import top.shyforrd.utilsserver.service.DictService;
import top.shyforrd.utilsserver.utils.BaseResult;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author 14645
 */
@RequestMapping("/common")
@RestController
@Validated
public class CommonController {
    @Resource
    private DictService dictService;

    @GetMapping("getDict")
    public BaseResult<Map<String, List<Dict>>> getDict(@Validated @RequestParam @NotBlank(message = "不能为空") String types) {
        String a = "";
        return dictService.getDict(types);
    }
}
