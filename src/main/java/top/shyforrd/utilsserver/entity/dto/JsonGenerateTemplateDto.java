package top.shyforrd.utilsserver.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;
import top.shyforrd.utilsserver.entity.JsonGenerateTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 14645
 */
@Data
@EqualsAndHashCode
public class JsonGenerateTemplateDto {
    private Integer id;

    /**
     * 对象key
     * 生成key
     */
    @NotBlank(message = "key不能为空")
    private String key;

    /**
     * 生成类型
     */
    @NotBlank(message = "类型不能为空")
    private String type;

    /**
     * 生成条件
     */
    private String condition;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 编号
     */
    private Integer number;
    private List<JsonGenerateTemplateDto> child;
}
