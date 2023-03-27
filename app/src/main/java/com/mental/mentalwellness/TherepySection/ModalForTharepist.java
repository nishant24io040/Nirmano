package com.mental.mentalwellness.TherepySection;

public class ModalForTharepist {
    String name,title,image,about,expect,experience,languages,offerTherapy,price,qualification;

    public ModalForTharepist() {
    }

    public ModalForTharepist(String name, String title, String image, String about, String expect,
                             String experience, String languages, String offerTherapy,
                             String price, String qualification) {
        this.name = name;
        this.title = title;
        this.image = image;
        this.about = about;
        this.expect = expect;
        this.experience = experience;
        this.languages = languages;
        this.offerTherapy = offerTherapy;
        this.price = price;
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getOfferTherapy() {
        return offerTherapy;
    }

    public void setOfferTherapy(String offerTherapy) {
        this.offerTherapy = offerTherapy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
