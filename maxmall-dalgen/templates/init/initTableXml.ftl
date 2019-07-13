<@pp.dropOutputFile />
<#import "../lib/lib.ftl" as lib/>
<#list dalgen.tables as table>
    <@pp.changeOutputFile name = "/${dalgen.tablesPath}/${table.sqlName}.xml" />
<!DOCTYPE table SYSTEM "../config/table-config-1.0.dtd">
<table sqlname="${table.sqlName}" physicalName="${table.physicalName}"<#if table.remark??> remark="${table.remark!}"</#if>>
    <!--  特殊字符说明  &lt;&gt;   <> -->

</table>
</#list>
