package com.instrantes.pojo;
public class PsCollection {
    private Integer collectionId;
    private Integer collectionUserid;
    private Integer collectionTagid;
    private String collectionPhotolocation;
    private String collectionPhotoname;
    private String collectionPhotointroduction;
    private Object collectionPrice;
    private String collectionPhotoinformation;
    private String collectionAdress;
    private Integer collectionFlag;
    private java.util.Date collectionCreatetime;
    private java.util.Date collectionUpdatetime;
    public PsCollection() {
        super();
    }
    public PsCollection(Integer collectionId,Integer collectionUserid,Integer collectionTagid,String collectionPhotolocation,String collectionPhotoname,String collectionPhotointroduction,Object collectionPrice,String collectionPhotoinformation,String collectionAdress,Integer collectionFlag,java.util.Date collectionCreatetime,java.util.Date collectionUpdatetime) {
        super();
        this.collectionId = collectionId;
        this.collectionUserid = collectionUserid;
        this.collectionTagid = collectionTagid;
        this.collectionPhotolocation = collectionPhotolocation;
        this.collectionPhotoname = collectionPhotoname;
        this.collectionPhotointroduction = collectionPhotointroduction;
        this.collectionPrice = collectionPrice;
        this.collectionPhotoinformation = collectionPhotoinformation;
        this.collectionAdress = collectionAdress;
        this.collectionFlag = collectionFlag;
        this.collectionCreatetime = collectionCreatetime;
        this.collectionUpdatetime = collectionUpdatetime;
    }
    public Integer getCollectionId() {
        return this.collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionUserid() {
        return this.collectionUserid;
    }

    public void setCollectionUserid(Integer collectionUserid) {
        this.collectionUserid = collectionUserid;
    }

    public Integer getCollectionTagid() {
        return this.collectionTagid;
    }

    public void setCollectionTagid(Integer collectionTagid) {
        this.collectionTagid = collectionTagid;
    }

    public String getCollectionPhotolocation() {
        return this.collectionPhotolocation;
    }

    public void setCollectionPhotolocation(String collectionPhotolocation) {
        this.collectionPhotolocation = collectionPhotolocation;
    }



    public String getCollectionPhotoname() {
        return this.collectionPhotoname;
    }

    public void setCollectionPhotoname(String collectionPhotoname) {
        this.collectionPhotoname = collectionPhotoname;
    }

    public String getCollectionPhotointroduction() {
        return this.collectionPhotointroduction;
    }

    public void setCollectionPhotointroduction(String collectionPhotointroduction) {
        this.collectionPhotointroduction = collectionPhotointroduction;
    }

    public Object getCollectionPrice() {
        return this.collectionPrice;
    }

    public void setCollectionPrice(Object collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getCollectionPhotoinformation() {
        return this.collectionPhotoinformation;
    }

    public void setCollectionPhotoinformation(String collectionPhotoinformation) {
        this.collectionPhotoinformation = collectionPhotoinformation;
    }

    public String getCollectionAdress() {
        return this.collectionAdress;
    }

    public void setCollectionAdress(String collectionAdress) {
        this.collectionAdress = collectionAdress;
    }

    public Integer getCollectionFlag() {
        return this.collectionFlag;
    }

    public void setCollectionFlag(Integer collectionFlag) {
        this.collectionFlag = collectionFlag;
    }

    public java.util.Date getCollectionCreatetime() {
        return this.collectionCreatetime;
    }

    public void setCollectionCreatetime(java.util.Date collectionCreatetime) {
        this.collectionCreatetime = collectionCreatetime;
    }

    public java.util.Date getCollectionUpdatetime() {
        return this.collectionUpdatetime;
    }

    public void setCollectionUpdatetime(java.util.Date collectionUpdatetime) {
        this.collectionUpdatetime = collectionUpdatetime;
    }

}
