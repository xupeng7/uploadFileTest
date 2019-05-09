package hello.model;


import javax.persistence.*;

@Entity
@Table(name ="akafutest" )
public class Akafu {
    @Id
    @GeneratedValue
    protected int  id;
    protected String originame;
    protected String imageUrl;
    protected int workerId;
    protected int type;//type 0是头像 type1是店铺资料



    @Column(name = "create_time")
    protected String createTime;


    public String getOriginame() {
        return originame;
    }

    public void setOriginame(String originame) {
        this.originame = originame;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
