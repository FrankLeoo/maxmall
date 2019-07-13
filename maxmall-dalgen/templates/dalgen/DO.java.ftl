<@pp.dropOutputFile />
<#list dalgen.dos as DO>
<@pp.changeOutputFile name = "/main/java/${DO.classPath}/${DO.className}.java" />
package ${DO.packageName};

import com.maxmall.common.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
<#list DO.importLists as import>
<#if !import?ends_with("${DO.className}")>
import ${import};
</#if>
</#list>

/**
 * The table ${DO.desc}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "${DO.tableName?lower_case}")
public class ${DO.className} extends BaseEntity {

    <#assign excloudComumn =["id","creator","creatorId","createTime","lastOperator","lastOperatorId","updateTime"]/>
    <#list DO.fieldses as fields>
    <#if !excloudComumn?seq_contains(fields.name)>
    /**
     * ${fields.desc}.
     */
    @Column(name = "${fields.name?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}")
    private ${fields.javaType} ${fields.name};

    </#if>
    </#list>
}
</#list>
