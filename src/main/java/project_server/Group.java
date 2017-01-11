package project_server;

import java.util.Date;

/**
 * Created by aleks on 09.01.2017.
 */
public class Group {
    private int id;

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    private String specialityName;
    private String groupCode;
    private Date createDate;

    public Group() {
    }

    public Group(int id, String specialityName, String groupCode, Date createDate) {
        this.id = id;
        this.specialityName = specialityName;
        this.groupCode = groupCode;
        this.createDate = createDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
