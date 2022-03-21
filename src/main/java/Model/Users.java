package Model;


import java.security.Timestamp;
import java.util.Date;

public class Users {
    private int userID;
    private String userName;
    private String password;
    private Date createDate;
    private String createBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Users(int userID, String userName, String password, Date createDate,String createBy, Timestamp lastUpdate, String lastUpdatedBy){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createBy = createBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * @return the userID
     */

    public int getUserID(){return userID;}
    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID){this.userID = userID;}
    /**
     * @return the userName
     */
    public String getUserName(){return userName;}
    /**
     * @param userName to userName to set
     */
    public void setUserName(String userName){this.userName = userName;}
    /**
     * @return the password
     */
    public String getPassword(){return password;}
    /**
     * @param password to password to set
     */
    public void setPassword(String password){this.password = password;}
    /**
     * @return the createDate
     */
    public Date getCreateDate(){return createDate;}
    /**
     * @param createDate to createDate to set
     */
    public void setCreateDate(Date createDate){this.createDate = createDate;}
    /**
     * @return the createBy
     */
    public String getCreateBy(){return createBy;}
    /**
     * @param createBy to createBy to set
     */
    public void setCreateBy(String createBy){this.createBy = createBy;}
    /**
     * @return the lastUpdate
     */
    public Timestamp getLastUpdate(){return lastUpdate;}
    /**
     * @param lastUpdate to lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate){this.lastUpdate = lastUpdate;}
    /**
     * @return the lastUpdatedBy
     */
    public String getLastUpdatedBy(){return  lastUpdatedBy;}
    /**
     * @param lastUpdatedBy to lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy){this.lastUpdatedBy = lastUpdatedBy;}
}
