package jt56.comm.code;

import jt56.comm.code.def.FtlDef;
import jt56.comm.code.factory.CodeGenerateFactory;


public class CodeUtil {

	public static void main(String[] args) {
		 /** 此处修改成你的 表名 和 中文注释***/
		 String tableName="aname"; //
		 String codeName ="测试功能";//中文注释  当然你用英文也是可以的 
		 String entityPackage ="zq";//实体包
		 String keyType = FtlDef.KEY_TYPE_01;//主键生成方式 01:UUID  02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName,entityPackage,keyType);
	}
}
