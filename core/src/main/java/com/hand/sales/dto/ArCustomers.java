package com.hand.sales.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
@ExtensionAttribute(disable=true)
@Table(name = "hap_ar_customers")
public class ArCustomers extends BaseDTO {

     public static final String FIELD_CUSTOMER_ID = "customerId";
     public static final String FIELD_CUSTOMER_NUMBER = "customerNumber";
     public static final String FIELD_CUSTOMER_NAME = "customerName";
     public static final String FIELD_COMPANY_ID = "companyId";
     public static final String FIELD_ENABLED_FLAG = "enabledFlag";
     public static final String FIELD_PROGRAM_APPLICATION_ID = "programApplicationId";
     public static final String FIELD_PROGRAM_UPDATE_DATE = "programUpdateDate";


     @Id
     @GeneratedValue
     private Long customerId; //客户ID

     @NotEmpty
     @Length(max = 100)
     private String customerNumber; //客户编号

     @NotEmpty
     @Length(max = 250)
     private String customerName; //客户名称

     @NotNull
     private Long companyId; //公司ID

     @NotEmpty
     @Length(max = 1)
     private String enabledFlag; // 启用标识

     private Long programApplicationId;

     private Date programUpdateDate;


     public void setCustomerId(Long customerId){
         this.customerId = customerId;
     }

     public Long getCustomerId(){
         return customerId;
     }

     public void setCustomerNumber(String customerNumber){
         this.customerNumber = customerNumber;
     }

     public String getCustomerNumber(){
         return customerNumber;
     }

     public void setCustomerName(String customerName){
         this.customerName = customerName;
     }

     public String getCustomerName(){
         return customerName;
     }

     public void setCompanyId(Long companyId){
         this.companyId = companyId;
     }

     public Long getCompanyId(){
         return companyId;
     }

     public void setEnabledFlag(String enabledFlag){
         this.enabledFlag = enabledFlag;
     }

     public String getEnabledFlag(){
         return enabledFlag;
     }

     public void setProgramApplicationId(Long programApplicationId){
         this.programApplicationId = programApplicationId;
     }

     public Long getProgramApplicationId(){
         return programApplicationId;
     }

     public void setProgramUpdateDate(Date programUpdateDate){
         this.programUpdateDate = programUpdateDate;
     }

     public Date getProgramUpdateDate(){
         return programUpdateDate;
     }

     }
