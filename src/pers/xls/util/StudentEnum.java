package pers.xls.util;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-26 17:22
 * @Modified by :
 */

//1.在读 2.休学 3.退学 4.删除
public enum StudentEnum {
    READING(1,"在读"),
    SUSPENSION(2,"休学"),
    DROPOUT(3,"退学"),
    DELETED(4,"删除");

    public final Integer type;
    public final String value;

     StudentEnum(Integer type,String value){
        this.type = type;
        this.value = value;
    }
}
