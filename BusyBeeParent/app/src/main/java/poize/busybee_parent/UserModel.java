package poize.busybee_parent;

import java.util.Date;

public class UserModel {
    private String parentName;
    private String email;
    private String pass;
    private String childName;
    private Date childDOB;
    private long honey;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public UserModel(String parentName, String email, String pass, String childName, Date childDOB, long honey) {
        this.parentName = parentName;
        this.email = email;
        this.pass = pass;
        this.childName = childName;
        this.childDOB = childDOB;
        this.honey = honey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Date getChildDOB() {
        return childDOB;
    }

    public void setChildDOB(Date childDOB) {
        this.childDOB = childDOB;
    }
}
