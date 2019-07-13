<@pp.dropOutputFile />
<#list dalgen.doMappers as doMapper>
<@pp.changeOutputFile name = "/main/java/${doMapper.classPath}/${doMapper.className}.java" />
package ${doMapper.packageName};

import com.maxmall.common.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
<#list doMapper.importLists as import>
import ${import};
</#list>

/**
 * The Table ${doMapper.tableName!}.
 * ${doMapper.desc!}
 */
@Mapper
@Component
public interface ${doMapper.className} extends MyMapper<${doMapper.className?replace('Mapper','')}> {


}
</#list>
